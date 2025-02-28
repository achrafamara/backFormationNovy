package org.example.projet.repositories;


import org.example.projet.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM task", countQuery = "SELECT COUNT(id) FROM task", nativeQuery = true)
    List<Category> findByNameL(Pageable pageable);
}

