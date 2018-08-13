package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.News;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsServiceTest {
    @Autowired
    private NewsService newsService;
    private News news;


    @Before
    public void setUp() throws Exception {
        news = new News();
        news.setId("111");
        news.setName("test 1");
        news.setContent("test content 1");
        news.setAuthor("shijin");
        news.setPicture("no picture");
        newsService.saveEntity(news);
    }

    @After
    public void tearDown() throws Exception {
        JSONObject Json = new JSONObject();
        Json.put("id",news.getId());
        newsService.deleteNews(Json);
    }

    @Test
    public void queryAllNews() {
        News temp = new News();
        temp.setId("333");
        temp.setName("test 3");
        temp.setContent("test content 3");
        temp.setAuthor("shijin");
        temp.setPicture("no picture");
        newsService.saveEntity(temp);

        JSONArray jsonArray = newsService.queryAllNews();
        int len=jsonArray.size();

        System.out.println(len);
        News test = new News();
        for(int i=0;i<len;i++){
            News test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), News.class);
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
        newsService.deleteNews(Json);
    }

    @Test
    public void queryNewsById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        News idea = JSONObject.toJavaObject(newsService.queryNewsById(Json), News.class);

        System.out.println(news);
        System.out.println(idea);
        assertEquals(idea,news);
    }

    @Test
    public void addNews() {
        News temp=new News();
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
        newsService.addNews(Json);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        News idea = JSONObject.toJavaObject(newsService.queryNewsById(jsonObject), News.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        assertEquals(idea,temp);



        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",temp.getId());
        newsService.deleteNews(jsonObject1);
    }

    @Test
    public void deleteNews() {
        News temp=new News();
        temp.setId("222");
        temp.setName("test 2");
        temp.setContent("test content 2");
        newsService.saveEntity(temp);

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        newsService.deleteNews(Json);

        News actual = JSONObject.toJavaObject(newsService.queryNewsById(Json), News.class);
        assertEquals(null,actual);

    }

    @Test
    public void editNews() {
        String id="111";
        String name="test 1";
        String content="edit success!";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("content",content);
        newsService.editNews(Json);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        News actual = JSONObject.toJavaObject(newsService.queryNewsById(jsonObject), News.class);
        System.out.println(content);
        System.out.println(actual.getContent());
        assertEquals(content,actual.getContent());
    }

    @Test
    public void bindNews() {
        String id="111";
        String picture="it's a new picture's name";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Json.put("picture",picture);
        newsService.bindNews(Json);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        News actual = JSONObject.toJavaObject(newsService.queryNewsById(jsonObject), News.class);
        System.out.println(picture);
        System.out.println(actual.getPicture());
        assertEquals(picture,actual.getPicture());
    }
}