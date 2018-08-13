package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.Guidance;
import com.seweb.backend.domain.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuidanceServiceTest {
    @Autowired
    private GuidanceService guidanceService;
    private Guidance guidance;


    @Before
    public void setUp() throws Exception {
        guidance = new Guidance();
        guidance.setId("111");
        guidance.setName("test 1");
        guidance.setContent("test content 1");

        guidance.setPicture("no picture");
        guidanceService.saveEntity(guidance);
    }

    @After
    public void tearDown() throws Exception {
        JSONObject Json = new JSONObject();
        Json.put("id",guidance.getId());
        guidanceService.deleteGuidance(Json);
    }

    @Test
    public void queryAllGuidance() {
        Guidance temp = new Guidance();
        temp.setId("333");
        temp.setName("test 3");
        temp.setContent("test content 3");

        temp.setPicture("no picture");
        guidanceService.saveEntity(temp);

        JSONArray jsonArray = guidanceService.queryAllGuidance();
        int len=jsonArray.size();
        System.out.println(len);
        Guidance test = new Guidance();
        for(int i=0;i<len;i++){
            String id=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Guidance.class).getId();
            System.out.println(JSONObject.toJavaObject(jsonArray.getJSONObject(i), Guidance.class).getId());
            if(id.equals("333")){
                test=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Guidance.class);
                break;
            }
        }

        System.out.println(test);
        temp.setCreatedTime(null);
        test.setCreatedTime(null);
        assertEquals(temp,test);
        //assertEquals(news,test);

        JSONObject Json = new JSONObject();
        Json.put("id",temp.getId());
        guidanceService.deleteGuidance(Json);
    }

    @Test
    public void queryGuidanceById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        Guidance idea = JSONObject.toJavaObject(guidanceService.queryGuidanceById(Json), Guidance.class);

        System.out.println(guidance);
        System.out.println(idea);
        assertEquals(idea,guidance);
    }

    @Test
    public void addGuidance() {
        Guidance temp=new Guidance();
        String id="222";
        String name="test 2";
        String content="test content 2";
        temp.setId(id);
        temp.setName(name);
        temp.setContent(content);

        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        guidanceService.addGuidance(Json);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        Guidance idea = JSONObject.toJavaObject(guidanceService.queryGuidanceById(jsonObject), Guidance.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        assertEquals(idea,temp);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",temp.getId());
        guidanceService.deleteGuidance(jsonObject1);
    }

    @Test
    public void deleteGuidance() {
        Guidance temp=new Guidance();
        temp.setId("222");
        temp.setName("test 2");
        temp.setContent("test content 2");
        guidanceService.saveEntity(temp);

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        guidanceService.deleteGuidance(Json);

        Guidance actual = JSONObject.toJavaObject(guidanceService.queryGuidanceById(Json), Guidance.class);
        assertEquals(null,actual);
    }

    @Test
    public void editGuidance() {
        String id="111";
        String name="test 1";
        String content="edit success!";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        guidanceService.editGuidance(Json);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        Guidance actual = JSONObject.toJavaObject(guidanceService.queryGuidanceById(jsonObject), Guidance.class);
        System.out.println(content);
        System.out.println(actual.getContent());
        assertEquals(content,actual.getContent());
    }

    @Test
    public void bindGuidance() {
        String id="111";
        String picture="it's a new picture's name";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("picture",picture);
        guidanceService.bindGuidance(Json);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        Guidance actual = JSONObject.toJavaObject(guidanceService.queryGuidanceById(jsonObject), Guidance.class);
        System.out.println(picture);
        System.out.println(actual.getPicture());
        assertEquals(picture,actual.getPicture());
    }
}