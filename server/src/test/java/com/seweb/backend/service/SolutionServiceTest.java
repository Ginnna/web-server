package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.Case;
import com.seweb.backend.domain.Solution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SolutionServiceTest {
    @Autowired
    private SolutionService solutionService;
    private Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution();
        solution.setId("111");
        solution.setName("test 1");
        solution.setContent("test content 1");
        solution.setIntroduction("test introduction 1");
        //guidance.setPicture("no picture");
        solutionService.saveEntity(solution);
    }

    @After
    public void tearDown() throws Exception {
        JSONObject Json = new JSONObject();
        Json.put("id",solution.getId());
        solutionService.deleteSolution(Json);
    }

    @Test
    public void queryAllSolution() {
        Solution temp = new Solution();
        temp.setId("333");
        temp.setName("test 3");
        temp.setContent("test content 3");
        temp.setIntroduction("test introduction 3");
        solutionService.saveEntity(temp);

        JSONArray jsonArray = solutionService.queryAllSolution();
        int len=jsonArray.size();

        System.out.println(len);
        Solution test = new Solution();
        for(int i=0;i<len;i++){
            Solution test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Solution.class);
            String id=test_tp.getId();
            System.out.println(id);
            if(id.equals("333")){
                test=test_tp;
                break;
            }
        }

        System.out.println(test);
        temp.setCreatedTime(null);
        test.setCreatedTime(null);
        assertEquals(temp,test);

        JSONObject Json = new JSONObject();
        Json.put("id",temp.getId());
        solutionService.deleteSolution(Json);
    }

    @Test
    public void querySolutionById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        Solution idea = JSONObject.toJavaObject(solutionService.querySolutionById(Json), Solution.class);

        System.out.println(solution);
        System.out.println(idea);
        assertEquals(idea,solution);
    }

    @Test
    public void addSolution() {
        Solution temp=new Solution();
        String id="222";
        String name="test 2";
        String content="test content 2";
        String introduction="test introduction 2";
        temp.setId(id);
        temp.setName(name);
        temp.setContent(content);
        temp.setIntroduction(introduction);

        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        Json.put("introduciton",introduction);
        solutionService.addSolution(Json);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        Solution idea = JSONObject.toJavaObject(solutionService.querySolutionById(jsonObject), Solution.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        assertEquals(idea,temp);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",temp.getId());
        solutionService.deleteSolution(jsonObject1);
    }

    @Test
    public void deleteSolution() {
        Solution temp=new Solution();
        temp.setId("222");
        temp.setName("test 2");
        temp.setContent("test content 2");
        temp.setIntroduction("test introduction 2");
        solutionService.saveEntity(temp);

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        solutionService.deleteSolution(Json);

        Solution actual = JSONObject.toJavaObject(solutionService.querySolutionById(Json), Solution.class);
        assertEquals(null,actual);
    }

    @Test
    public void editSolution() {
        String id="111";
        String name="test 1";
        String content="edit success!";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        solutionService.editSolution(Json);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        Solution actual = JSONObject.toJavaObject(solutionService.querySolutionById(jsonObject), Solution.class);
        System.out.println(content);
        System.out.println(actual.getContent());
        assertEquals(content,actual.getContent());
    }
}