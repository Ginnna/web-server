package com.seweb.backend.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    private String id;

    private String code;

    private String name;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "created_user_id")
    private String createdUserId;

    @Column(name = "altered_time")
    private String alteredTime;

    @Column(name = "altered_user_id")
    private String alteredUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getAlteredTime() {
        return alteredTime;
    }

    public void setAlteredTime(String alteredTime) {
        this.alteredTime = alteredTime;
    }

    public String getAlteredUserId() {
        return alteredUserId;
    }

    public void setAlteredUserId(String alteredUserId) {
        this.alteredUserId = alteredUserId;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        if(obj == null || (obj.getClass() != this.getClass()))
        {
            return false;
        }

        try
        {
            return ((BaseEntity)obj).getId().equals(this.getId());
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return this.getId().hashCode();
    }

    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
