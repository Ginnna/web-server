package com.seweb.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_sys_client")
public class Client extends User implements Serializable {

    private static final long serialVersionUID = -4439189684704713043L;

    private String corporation;

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }
}
