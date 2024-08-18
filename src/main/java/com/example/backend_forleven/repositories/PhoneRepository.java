package com.example.backend_forleven.repositories;

import com.example.backend_forleven.models.PhoneList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneList, UUID> {

}
