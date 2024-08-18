package com.example.backend_forleven.controllers;

import com.example.backend_forleven.services.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/phone")
@RequiredArgsConstructor
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteNumber(@PathVariable UUID id) {
        var phoneId = phoneService.getPhoneId(id);
        if(phoneId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone not Found");
        }

        phoneService.deletePhone(id);
        return ResponseEntity.status(HttpStatus.OK).body("Phone deleted successfully");
    }



}
