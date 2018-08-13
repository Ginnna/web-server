package com.seweb.backend.domain;

import javax.persistence.*;

//解决方案
@Entity
@Table(name = "tbl_text_solution")
public class Solution extends Text{
    private int category;
    private String introduction;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}