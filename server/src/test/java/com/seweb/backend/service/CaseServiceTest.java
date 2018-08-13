package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.Case;
import com.seweb.backend.domain.Contact;
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
public class CaseServiceTest {
    @Autowired
    private CaseService caseService;
    private com.seweb.backend.domain.Case cs;

    @Before
    public void setUp() throws Exception {
        cs = new Case();
        cs.setId("111");
        cs.setName("test 1");
        cs.setContent("test content 1");

        //guidance.setPicture("no picture");
        caseService.saveEntity(cs);
    }

    @After
    public void tearDown() throws Exception {
        JSONObject Json = new JSONObject();
        Json.put("id",cs.getId());
        caseService.deleteCase(Json);
    }

    @Test
    public void queryAllCase() {
        Case temp = new Case();
        temp.setId("333");
        temp.setName("test 3");
        temp.setContent("test content 3");
        caseService.saveEntity(temp);

        JSONArray jsonArray = caseService.queryAllCase();
        int len=jsonArray.size();

        System.out.println(len);
        Case test = new Case();
        for(int i=0;i<len;i++){
            Case test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Case.class);
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
        caseService.deleteCase(Json);
    }

    @Test
    public void queryCaseById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        Case idea = JSONObject.toJavaObject(caseService.queryCaseById(Json), Case.class);

        System.out.println(cs);
        System.out.println(idea);
        assertEquals(idea,cs);
    }

    @Test
    public void addCase() {
        Case temp=new Case();
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
        caseService.addCase(Json);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        Case idea = JSONObject.toJavaObject(caseService.queryCaseById(jsonObject), Case.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        assertEquals(idea,temp);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",temp.getId());
        caseService.deleteCase(jsonObject1);
    }

    @Test
    public void deleteCase() {
        Case temp=new Case();
        temp.setId("222");
        temp.setName("test 2");
        temp.setContent("test content 2");
        caseService.saveEntity(temp);

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        caseService.deleteCase(Json);

        Case actual = JSONObject.toJavaObject(caseService.queryCaseById(Json), Case.class);
        assertEquals(null,actual);
    }

    @Test
    public void editCase() {
        String id="111";
        String name="test 1";
        String content="edit success!";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        caseService.editCase(Json);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        Case actual = JSONObject.toJavaObject(caseService.queryCaseById(jsonObject), Case.class);
        System.out.println(content);
        System.out.println(actual.getContent());
        assertEquals(content,actual.getContent());
    }
}