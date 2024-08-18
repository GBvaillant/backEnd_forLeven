package com.example.backend_forleven.services;


import com.example.backend_forleven.models.PhoneList;
import com.example.backend_forleven.models.StudentModel;
import com.example.backend_forleven.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private PhoneService phoneService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create Student successfully")
    void createStudentCase1() {
        List<PhoneList> phones = new ArrayList<>();
        StudentModel student = new StudentModel(UUID.randomUUID(), "Gabriel", "Vaillant", "2024123456", phones);

        when(studentRepository.save(any(StudentModel.class))).thenReturn(student);

        StudentModel createStudent = studentService.createStudent(student);

        verify(studentRepository, times(1)).save(any(StudentModel.class));

        assertThat(createStudent).isEqualTo(student);

    }

    @Test
    @DisplayName("Should create Student unsuccessfully")
    void createStudentCase2() {
        List<PhoneList> phones = new ArrayList<>();
        StudentModel student = new StudentModel(UUID.randomUUID(), "Gabriel", "Vaillant", "2024123456", phones);

        StudentModel createStudent = studentService.createStudent(student);
        when(studentRepository.save(any(StudentModel.class))).thenReturn(student);

        assertThat(createStudent).isNotEqualTo(student);

    }


    @Test
    @DisplayName("Should get all student successfully")
    void getAllStudentTestCase1() {
        List<PhoneList> phones1 = new ArrayList<>();
        StudentModel student1 = new StudentModel(UUID.randomUUID(), "Gabriel", "Vaillant", "2024123456", phones1);

        List<PhoneList> phones2 = new ArrayList<>();
        StudentModel student2 = new StudentModel(UUID.randomUUID(), "Eduarda", "Silva", "2024654321", phones2);

        List<StudentModel> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<StudentModel> result = studentService.getAllStudent();

        verify(studentRepository).findAll();

        assertThat(result).isEqualTo(students);
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Should get all student unsuccessfully")
    void getAllStudentTestCase2() {
        List<PhoneList> phones1 = new ArrayList<>();
        StudentModel student1 = new StudentModel(UUID.randomUUID(), "Gabriel", "Vaillant", "2024123456", phones1);

        List<PhoneList> phones2 = new ArrayList<>();
        StudentModel student2 = new StudentModel(UUID.randomUUID(), "Eduarda", "Silva", "2024654321", phones2);

        List<StudentModel> students = Arrays.asList(student1, student2);

        List<StudentModel> result = studentService.getAllStudent();

        verify(studentRepository).findAll();

        assertThat(result).isNotEqualTo(students);
        assertThat(result.size()).isNotEqualTo(2);
    }

    @Test
    @DisplayName("Should return registration student")
    void getByRegistrationTestCase1() {
        String registration = "2024123456";
        StudentModel student = new StudentModel();
        student.setRegistration(registration);


        when(studentRepository.findByRegistration(registration)).thenReturn(Optional.of(student));

        StudentModel result = studentService.getByRegistration(registration);

        assertThat(result).isEqualTo(student);

    }

    @Test
    @DisplayName("Should not return registration student")
    void getByRegistrationTestCase2() {
        String registration = "2024123456";
        StudentModel student = new StudentModel();
        student.setRegistration(registration);

        StudentModel result = studentService.getByRegistration(registration);

        assertThat(result).isNotEqualTo(student);
    }

    @Test
    @DisplayName("Should return id student")
    void getByIdTestCase1() {
        UUID id = UUID.randomUUID();
        StudentModel student = new StudentModel();
        student.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        StudentModel result = studentService.getId(id);

        assertThat(result).isEqualTo(student);
    }

    @Test
    @DisplayName("Should not return id student")
    void getByIdTestCase2() {

        UUID id = UUID.randomUUID();
        StudentModel student = new StudentModel();
        student.setId(id);

        StudentModel result = studentService.getId(id);

        assertThat(result).isNotEqualTo(student);
    }

    @Test
    @DisplayName("Should update student successfully")
    void updateStudentTestCase1() {
        UUID id = UUID.randomUUID();
        StudentModel student = new StudentModel();
        student.setId(id);
        student.setName("Gabriel");
        student.setLastName("Vaillant");

        when(studentRepository.existsById(id)).thenReturn(true);
        when(studentRepository.save(student)).thenReturn(student);

        StudentModel result = studentService.updateStudent(student);

        assertThat(result).isEqualTo(student);
        verify(studentRepository, times(1)).save(student);

    }

    @Test
    @DisplayName("Should update student unsuccessfully")
    void updateStudentTestCase2() {

        UUID id = UUID.randomUUID();
        StudentModel student = new StudentModel();
        student.setId(id);

        when(studentRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            studentService.updateStudent(student);
        });

        assertEquals("student not found", exception.getMessage());
        verify(studentRepository, never()).save(any(StudentModel.class));
    }

    @Test
    @DisplayName("Should delete student successfully")
    void deleteStudentByIdTestCase1() {
        UUID id = UUID.randomUUID();

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id );
    }


}