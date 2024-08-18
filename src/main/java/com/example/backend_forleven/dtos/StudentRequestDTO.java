package com.example.backend_forleven.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record StudentRequestDTO(
        @NotBlank @Size(min = 1, max = 100) String name,
        @NotBlank @Size(min = 1, max = 100) String lastName,
        @Size(min = 1) List<String> phones) {
}
