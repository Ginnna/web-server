package com.seweb.backend.domain;

import javax.persistence.*;

//行业新闻
@Entity
@Table(name = "tbl_text_news")
public class News extends Text{
    private String author;
    private String picture;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
