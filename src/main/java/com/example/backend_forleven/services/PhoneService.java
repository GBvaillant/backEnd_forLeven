package com.example.backend_forleven.services;

import com.example.backend_forleven.models.PhoneList;
import com.example.backend_forleven.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public PhoneList getPhoneId (UUID id) {
        return phoneRepository.getById(id);
    }

    public void deletePhone (UUID id) {
       phoneRepository.deleteById(id);
    }
}
