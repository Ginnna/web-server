package com.seweb.backend.service;

import com.seweb.backend.domain.Client;
import com.seweb.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends UserService<Client>{

    @Autowired
    private ClientRepository clientRepository;
}
