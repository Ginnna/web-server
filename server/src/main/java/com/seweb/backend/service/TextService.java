package com.seweb.backend.service;

import com.seweb.backend.domain.BaseEntity;
import com.seweb.backend.domain.Text;
import com.seweb.backend.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TextService<T extends Text> extends BaseService<T>{

    public TextRepository<T> textRepository;

    @Autowired
    public void setTextRepository(TextRepository<T> textRepository) {
        this.textRepository = textRepository;
    }
}
