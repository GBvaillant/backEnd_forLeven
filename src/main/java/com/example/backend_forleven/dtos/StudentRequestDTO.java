package com.example.backend_forleven.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record StudentRequestDTO(
        @NotBlank(message = "Name must not be blank") String name,
        @NotBlank(message = "Last name must not be blank") String lastName,
        @Size(min = 1, message = "At least one phone number must be provided") List<String> phones) {
}
