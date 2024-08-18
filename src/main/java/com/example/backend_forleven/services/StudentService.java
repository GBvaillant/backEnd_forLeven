package com.example.backend_forleven.services;

import com.example.backend_forleven.models.PhoneList;
import com.example.backend_forleven.models.StudentModel;
import com.example.backend_forleven.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentModel createStudent(StudentModel student) {
        return studentRepository.save(student);
    }

    public List<StudentModel> getAllStudent() {
        return studentRepository.findAll();
    }

    public StudentModel getByRegistration (String registration) {
        return studentRepository.findByRegistration(registration).orElse(null);
    }

    public StudentModel getId (UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public StudentModel updateStudent(StudentModel student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        throw new EntityNotFoundException("student not found");
    }

    public void deleteStudent (UUID id) {
        studentRepository.deleteById(id);
    }
}