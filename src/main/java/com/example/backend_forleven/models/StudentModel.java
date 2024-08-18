package com.example.backend_forleven.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "student")

public class StudentModel {

    @Id
    @GeneratedValue
    UUID id;

    @Size(min = 3)
    private String name;
    @Size(min = 3)
    private String lastName;


    @Column(unique = true)
    @Size(min = 3)
    private String registration;

    @PrePersist
    protected void onCreate() {
        this.registration = createRegistration();
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneList> phones = new ArrayList<>();


    private String createRegistration() {
        String yearNow = String.valueOf(Year.now().getValue());
        int numberRandom = new Random().nextInt(900000) + 100000;
        return yearNow + numberRandom;
    }
}


