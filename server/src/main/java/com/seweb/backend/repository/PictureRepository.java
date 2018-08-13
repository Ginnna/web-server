package com.seweb.backend.repository;

import com.seweb.backend.domain.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends BaseRepository<Picture>{

    List<Picture> findByCode(String code);
    Picture findByName(String name);
    void deleteByName(String name);
}
