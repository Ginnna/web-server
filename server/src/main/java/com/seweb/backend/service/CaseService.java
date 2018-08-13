package com.seweb.backend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.domain.Case;
import com.seweb.backend.domain.News;
import com.seweb.backend.framework.utils.json.JsonUtil;
import com.seweb.backend.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CaseService extends BaseService<Case>{
    @Autowired
    private CaseRepository caseRepository;

    public JSONArray queryAllCase() {
        return JsonUtil.toJSONArray(caseRepository.findAll());
    }

    public JSONObject queryCaseById(JSONObject params) {
        String id = params.getString("id");
        return JSON.parseObject(JSON.toJSONString(caseRepository.findById(id)));
    }

    public void addCase(JSONObject params) {
        Case cs = JSONObject.toJavaObject(params, Case.class);
        String id=UUID.randomUUID().toString();
        if(cs.getId()==null){
            cs.setId(id);
        }
        //cs.setId(UUID.randomUUID().toString());
        this.saveEntity(cs);
    }

    public void deleteCase(JSONObject params) {
        String id = params.getString("id");
        this.deleteEntity(id);
    }


    public void editCase(JSONObject params) {
        //Case cs = JSONObjec""t.toJavaObject(params, Case.class);
        //this.updateEntity(cs);
        String id = params.getString("id");
        String name=params.getString("name");
        String content=params.getString("content");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(caseRepository.findById(id)));
        Case cs = JSONObject.toJavaObject(tp, Case.class);
        if(name!=null)
            cs.setName(name);
        if(content!=null)
            cs.setContent(content);
        this.updateEntity(cs);
    }
}
