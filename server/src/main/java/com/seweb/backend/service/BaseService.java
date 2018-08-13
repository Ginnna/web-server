package com.seweb.backend.service;

import com.seweb.backend.domain.BaseEntity;
import com.seweb.backend.domain.User;
import com.seweb.backend.framework.utils.date.DateUtil;
import com.seweb.backend.framework.utils.string.StringUtil;
import com.seweb.backend.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, RuntimeException.class})
public class BaseService<T extends BaseEntity>
{
    public BaseRepository<T> baseRepository;

    @Autowired
    public void setBaseRepository(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T saveEntity(T entity)
    {
        String id = entity.getId();
        if(StringUtil.isEmpty(id))
        {
            id = UUID.randomUUID().toString();
            entity.setId(id);
        }



        entity.setCreatedTime(DateUtil.formatDateTime(new Date()));

        baseRepository.save(entity);
        return entity;
    }

    public T saveEntity(T entity, User user)
    {
        String id = entity.getId();
        if(StringUtil.isEmpty(id))
        {
            id = UUID.randomUUID().toString();
            entity.setId(id);
        }

        if(user != null)
        {
            entity.setCreatedUserId(user.getId());
        }

        entity.setCreatedTime(DateUtil.formatDateTime(new Date()));

        baseRepository.save(entity);
        return entity;
    }

    public void deleteEntity(String id)
    {
        baseRepository.deleteById(id);
    }
    public T updateEntity(T entity)
    {

        entity.setAlteredTime(DateUtil.formatTime(new Date()));

        baseRepository.save(entity);
        return entity;
    }
}
