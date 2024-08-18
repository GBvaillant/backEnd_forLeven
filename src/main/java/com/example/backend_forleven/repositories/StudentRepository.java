package com.example.backend_forleven.repositories;

import com.example.backend_forleven.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
    Optional<StudentModel> findByRegistration(String registration);
}
