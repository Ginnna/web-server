package com.seweb.backend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.domain.News;
import com.seweb.backend.domain.Solution;
import com.seweb.backend.framework.utils.json.JsonUtil;
import com.seweb.backend.repository.NewsRepository;
import com.seweb.backend.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SolutionService extends BaseService<Solution>{
    @Autowired
    private SolutionRepository solutionRepository;

    public JSONArray queryAllSolution(){
        //一键查询，返回所有
        return JsonUtil.toJSONArray(solutionRepository.findAll());
    }


    public JSONObject querySolutionById(JSONObject params) {
        //根据id查询
        String id = params.getString("id");
        return JSON.parseObject(JSON.toJSONString(solutionRepository.findById(id)));
    }

    public void addSolution(JSONObject params) {
        //params包括name、content
        Solution solution = JSONObject.toJavaObject(params, Solution.class);
        String id=UUID.randomUUID().toString();
        if(solution.getId()==null){
            solution.setId(id);
        }
        //solution.setId(UUID.randomUUID().toString());
        this.saveEntity(solution);
    }

    public void deleteSolution(JSONObject params) {
        //根据id删除
        String id = params.getString("id");
        this.deleteEntity(id);
    }

    public void editSolution(JSONObject params) {
        //params包括id、name、content、introduction其中，以id为主
        //其他消息会被置为null
        String id = params.getString("id");
        String name=params.getString("name");
        String content=params.getString("content");
        String introduction=params.getString("introduction");

        JSONObject tp=JSON.parseObject(JSON.toJSONString(solutionRepository.findById(id)));
        Solution solution = JSONObject.toJavaObject(tp, Solution.class);
        if(name!=null)
            solution.setName(name);
        if(content!=null)
            solution.setContent(content);
        if(introduction!=null)
            solution.setIntroduction(introduction);
        this.updateEntity(solution);
    }
}
