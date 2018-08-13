package com.seweb.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

//成功案例
@Entity
@Table(name = "tbl_text_case")
public class Case extends Text{
    private int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
