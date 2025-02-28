package org.example.projet.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projet.model.Employee;

import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private long employeeId;

    @Transient
    private Employee employee;
}
