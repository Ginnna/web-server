package com.seweb.backend.domain;

import javax.persistence.*;

@MappedSuperclass
public class Text extends BaseEntity{

    @Column(length = 102400)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
