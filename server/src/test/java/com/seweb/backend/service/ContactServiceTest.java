package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.Contact;
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
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;
    private Contact contact;

    @Before
    public void setUp() throws Exception {
        contact = new Contact();
        contact.setId("111");
        contact.setName("test 1");
        contact.setContent("test content 1");

        //guidance.setPicture("no picture");
        contactService.saveEntity(contact);
    }

    @After
    public void tearDown() throws Exception {
        JSONObject Json = new JSONObject();
        Json.put("id",contact.getId());
        contactService.deleteContact(Json);
    }

    @Test
    public void queryAllContact() {
        Contact temp = new Contact();
        temp.setId("333");
        temp.setName("test 3");
        temp.setContent("test content 3");
        contactService.saveEntity(temp);

        JSONArray jsonArray = contactService.queryAllContact();
        int len=jsonArray.size();

        System.out.println(len);
        Contact test = new Contact();
        for(int i=0;i<len;i++){
            Contact test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Contact.class);
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
        contactService.deleteContact(Json);
    }

    @Test
    public void queryContactById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        Contact idea = JSONObject.toJavaObject(contactService.queryContactById(Json), Contact.class);

        System.out.println(contact);
        System.out.println(idea);
        assertEquals(idea,contact);
    }

    @Test
    public void addContact() {
        Contact temp=new Contact();
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
        contactService.addContact(Json);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        Contact idea = JSONObject.toJavaObject(contactService.queryContactById(jsonObject), Contact.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        assertEquals(idea,temp);



        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",temp.getId());
        contactService.deleteContact(jsonObject1);
    }

    @Test
    public void deleteContact() {
        Contact temp=new Contact();
        temp.setId("222");
        temp.setName("test 2");
        temp.setContent("test content 2");
        contactService.saveEntity(temp);

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        contactService.deleteContact(Json);

        Contact actual = JSONObject.toJavaObject(contactService.queryContactById(Json), Contact.class);
        assertEquals(null,actual);
    }

    @Test
    public void editContact() {
        String id="111";
        String name="test 1";
        String content="edit success!";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        contactService.editContact(Json);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        Contact actual = JSONObject.toJavaObject(contactService.queryContactById(jsonObject), Contact.class);
        System.out.println(content);
        System.out.println(actual.getContent());
        assertEquals(content,actual.getContent());
    }
}