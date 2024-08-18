package com.example.backend_forleven.controllers;

import com.example.backend_forleven.dtos.StudentRequestDTO;
import com.example.backend_forleven.models.PhoneList;
import com.example.backend_forleven.models.StudentModel;
import com.example.backend_forleven.services.PhoneService;
import com.example.backend_forleven.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private PhoneService phoneService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid StudentRequestDTO studentDto) {
        StudentModel student = new StudentModel();
        student.setName(studentDto.name());
        student.setLastName(studentDto.lastName());

        for (String phoneNumber : studentDto.phones()) {
            PhoneList phone = new PhoneList();
            phone.setNumber(phoneNumber);
            phone.setStudent(student);
            student.getPhones().add(phone);
        }
        StudentModel newStudent = studentService.createStudent(student);
        BeanUtils.copyProperties(studentDto, student);
        return ResponseEntity.status(HttpStatus.OK).body(newStudent);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> lisStudent() {
        var studentList = studentService.getAllStudent();
        return ResponseEntity.ok().body(studentList);
    }

    @GetMapping("/listRegister/{registration}")
    public ResponseEntity<Object> listRegister(@PathVariable String registration) {
        StudentModel studentRegister = this.studentService.getByRegistration(registration);
        if (studentRegister == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentRegister);
    }

    @GetMapping("/listId/{id}")
    public ResponseEntity<Object> listById(@PathVariable UUID id) {
        StudentModel studentId = this.studentService.getId(id);
        if(studentId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable UUID id, @RequestBody @Valid StudentRequestDTO studentDto) {
        StudentModel studentId = studentService.getId(id);
        if (studentId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found !!");
        }
        studentId.setName(studentDto.name());
        studentId.setLastName(studentDto.lastName());

        for (String phoneNumber : studentDto.phones()) {
            PhoneList phone = new PhoneList();
            phone.setNumber(phoneNumber);
            phone.setStudent(studentId);
            studentId.getPhones().add(phone);
        }

        StudentModel updateStudent = studentService.updateStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(updateStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable UUID id) {
        var studentId = studentService.getId(id);
        if (studentId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }

}
