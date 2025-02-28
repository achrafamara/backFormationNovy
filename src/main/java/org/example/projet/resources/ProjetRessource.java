package org.example.projet.resources;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.projet.entities.Project;
import org.example.projet.service.ProjectService;
import org.example.projet.service.dtos.ProjectDto;
import org.example.projet.service.dtos.ProjectFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjetRessource {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> findAll(ProjectFilter projectFilter, Pageable pageable) {
        projectFilter.setPageable(pageable);
        return ResponseEntity.ok(projectService.findAll(projectFilter));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> save(@Valid @RequestBody ProjectDto projectDto) {
        return ResponseEntity.created(URI.create("/projects"))
                .body(projectService.save(projectDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> update(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.update(id, projectDto));
    }

    @GetMapping("/with-category")
    public ResponseEntity<List<Project>> findAllWithCategory() {
        List<Project> projects = projectService.findAllWithCategory();

        return ResponseEntity.ok(projects);


    }
}

