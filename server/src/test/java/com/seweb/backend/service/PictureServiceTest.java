package com.seweb.backend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.BackendApplication;
import com.seweb.backend.domain.News;
import com.seweb.backend.domain.Picture;
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
@SpringBootTest(classes = BackendApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PictureServiceTest {
    @Autowired
    private PictureService pictureService;
    private Picture picture;

    @Before
    public void setUp() throws Exception {
        picture = new Picture();
        picture.setId("111");
        picture.setCode("xxx");
        picture.setName("test 1");

        pictureService.saveEntity(picture);
    }

    @After
    public void tearDown() throws Exception {
        String name=picture.getName();
        pictureService.deletePicture(name);
    }

    @Test
    public void queryAllPicture() {
        Picture temp = new Picture();
        temp.setId("333");
        temp.setCode("zzz");
        temp.setName("test 3");
        pictureService.saveEntity(temp);

        JSONArray jsonArray = pictureService.queryAllPicture();
        int len=jsonArray.size();

        System.out.println(len);
        Picture test = new Picture();
        for(int i=0;i<len;i++){
            Picture test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Picture.class);
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

        String temp_name=temp.getName();
        pictureService.deletePicture(temp_name);
    }

    @Test
    public void queryPictureById() {
        String id="111";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        System.out.println(Json);

        Picture idea = JSONObject.toJavaObject(pictureService.queryPictureById(Json), Picture.class);

        System.out.println(picture);
        System.out.println(idea);
        assertEquals(idea,picture);
    }

    @Test
    public void queryPictureByCode() {
        Picture temp = new Picture();
        temp.setId("333");
        temp.setCode("zzz");
        temp.setName("test 3");
        pictureService.saveEntity(temp);

        String code="zzz";
        JSONObject Json = new JSONObject();
        Json.put("code",code);
        System.out.println(Json);
        JSONArray jsonArray = pictureService.queryPictureByCode(Json);
        int len=jsonArray.size();

        System.out.println(len);
        Picture test = new Picture();
        for(int i=0;i<len;i++){
            Picture test_tp=JSONObject.toJavaObject(jsonArray.getJSONObject(i), Picture.class);
            String test_code=test_tp.getCode();
            System.out.println(test_code);
            if(test_code.equals("zzz")){
                test=test_tp;
                break;
            }
        }

        System.out.println(test);
        temp.setCreatedTime(null);
        test.setCreatedTime(null);
        assertEquals(temp,test);

        String temp_name=temp.getName();
        pictureService.deletePicture(temp_name);
    }

    @Test
    public void addPicture() {
        Picture temp=new Picture();
        String code="yyy";
        String name="test 2";
        temp.setCode(code);
        temp.setName(name);
        pictureService.addPicture(name,code);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        Picture idea = JSONObject.toJavaObject(pictureService.queryPictureByName(jsonObject), Picture.class);
        System.out.println(idea);
        idea.setCreatedTime(null);
        System.out.println(temp);
        temp.setId(idea.getId());
        assertEquals(idea,temp);

        pictureService.deletePicture(temp.getName());
    }

    @Test
    public void deletePicture() {
        Picture temp=new Picture();
        temp.setId("222");
        temp.setCode("yyy");
        temp.setName("test 2");
        pictureService.saveEntity(temp);

        pictureService.deletePicture("test 2");

        String id="222";
        JSONObject Json = new JSONObject();
        Json.put("id",id);
        Solution actual = JSONObject.toJavaObject(pictureService.queryPictureById(Json), Solution.class);
        assertEquals(null,actual);
    }

}