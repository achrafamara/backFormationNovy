package org.example.projet.service.dtos;

import jakarta.validation.constraints.NotBlank;
import org.example.projet.model.Employee;


public record ProjectDto(Long id, @NotBlank String projectName, Long employeeId) {
}