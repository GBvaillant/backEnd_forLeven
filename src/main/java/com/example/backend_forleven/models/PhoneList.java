package com.example.backend_forleven.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class PhoneList {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(min = 9, max = 9)
    private String number;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private StudentModel student;
}
