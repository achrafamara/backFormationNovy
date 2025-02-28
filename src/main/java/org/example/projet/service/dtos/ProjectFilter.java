package org.example.projet.service.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.projet.model.Employee;
import org.springframework.data.domain.Pageable;

@Getter
@Setter

public class ProjectFilter {
    private Long id;
    private String projectName;
    private Pageable pageable;
    private Long employeeId;
}
