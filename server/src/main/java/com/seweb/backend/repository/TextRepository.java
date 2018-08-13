package com.seweb.backend.repository;

import com.seweb.backend.domain.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TextRepository<T extends BaseEntity> extends BaseRepository<T> {

}
