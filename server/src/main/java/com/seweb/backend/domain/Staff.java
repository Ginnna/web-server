package com.seweb.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_sys_staff")
public class Staff extends User implements Serializable {

    private static final long serialVersionUID = 5721703468156919856L;

    private byte admin;

    public byte getAdmin() {
        return admin;
    }

    public void setAdmin(byte admin) {
        this.admin = admin;
    }
}
