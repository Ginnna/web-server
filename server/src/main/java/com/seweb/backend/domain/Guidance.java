package com.seweb.backend.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

//服务中心
@Entity
@Table(name = "tbl_text_guidance")
public class Guidance extends Text{
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
