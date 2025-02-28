package org.example.projet.repositories;


import org.example.projet.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p JOIN FETCH p.category")
    List<Project> findAllWithCategory();
}

