package com.seweb.backend.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.domain.News;
import com.seweb.backend.framework.utils.json.JsonUtil;
import com.seweb.backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import java.util.List;
import java.util.UUID;

@Service
public class NewsService extends BaseService<News> {

    @Autowired
    private NewsRepository newsRepository;

    public JSONArray queryAllNews(){
        //一键查询，返回所有
        return JsonUtil.toJSONArray(newsRepository.findAll());
    }


    public JSONObject queryNewsById(JSONObject params) {
        //根据id查询
        String id = params.getString("id");
        return JSON.parseObject(JSON.toJSONString(newsRepository.findById(id)));
    }

    public JSONObject addNews(JSONObject params) {
        //params包括name、content、author
        News news = JSONObject.toJavaObject(params, News.class);
        String id=UUID.randomUUID().toString();
        if(news.getId()==null){
            news.setId(id);
        }

        this.saveEntity(news);
        //把String 类型的id写入一个json包
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        return Json;
    }

    public void deleteNews(JSONObject params) {
        //根据id删除
        String id = params.getString("id");
        this.deleteEntity(id);
    }

    public void editNews(JSONObject params) {
        //params包括id、name、content其中，以id为主
        String id = params.getString("id");
        String name=params.getString("name");
        String content=params.getString("content");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(newsRepository.findById(id)));
        News news = JSONObject.toJavaObject(tp, News.class);
        if(name!=null)
            news.setName(name);
        if(content!=null)
            news.setContent(content);
        this.updateEntity(news);

    }
    public void bindNews(JSONObject params) {
        //params包括id、picture其中，以id为主
        String id = params.getString("id");
        String picture = params.getString("picture");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(newsRepository.findById(id)));
        News news = JSONObject.toJavaObject(tp, News.class);
        news.setPicture(picture);
        this.updateEntity(news);
    }
}
