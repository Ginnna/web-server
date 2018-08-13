package com.seweb.backend.repository;

import com.seweb.backend.domain.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends TextRepository<Contact>{
}
