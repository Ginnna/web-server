package com.seweb.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

//关于我们
@Entity
@Table(name = "tbl_text_about")
public class About extends Text implements Serializable {

    private static final long serialVersionUID = 3370323311234791174L;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
