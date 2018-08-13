package com.seweb.backend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.domain.Guidance;
import com.seweb.backend.domain.News;
import com.seweb.backend.framework.utils.json.JsonUtil;
import com.seweb.backend.repository.GuidanceRepository;
import com.seweb.backend.repository.NewsRepository;
import com.seweb.backend.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GuidanceService extends BaseService<Guidance> {
    @Autowired
    private GuidanceRepository guidanceRepository;

    public JSONArray queryAllGuidance(){
        //一键查询，返回所有
        return JsonUtil.toJSONArray(guidanceRepository.findAll());
    }


    public JSONObject queryGuidanceById(JSONObject params) {
        //根据id查询
        String id = params.getString("id");
        return JSON.parseObject(JSON.toJSONString(guidanceRepository.findById(id)));
    }

    public void addGuidance(JSONObject params) {
        //params包括name、content
        Guidance guidance = JSONObject.toJavaObject(params, Guidance.class);
        String id=UUID.randomUUID().toString();
        if(guidance.getId()==null)
            guidance.setId(id);
        this.saveEntity(guidance);
    }

    public void deleteGuidance(JSONObject params) {
        //根据id删除
        String id = params.getString("id");
        this.deleteEntity(id);
    }

    public void editGuidance(JSONObject params) {
        //params包括id、name、content其中，以id为主
        //其他消息会被置为null
        String id = params.getString("id");
        String name=params.getString("name");
        String content=params.getString("content");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(guidanceRepository.findById(id)));
        Guidance guidance = JSONObject.toJavaObject(tp, Guidance.class);
        if(name!=null)
            guidance.setName(name);
        if(content!=null)
            guidance.setContent(content);
        this.updateEntity(guidance);
    }
    public void bindGuidance(JSONObject params) {
        //params包括id、picture其中，以id为主
        String id = params.getString("id");
        String picture = params.getString("picture");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(guidanceRepository.findById(id)));
        Guidance guidance = JSONObject.toJavaObject(tp, Guidance.class);
        guidance.setPicture(picture);
        this.updateEntity(guidance);
    }
}
