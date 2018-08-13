package com.seweb.backend.repository;

import com.seweb.backend.domain.News;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends TextRepository<News> {

}
