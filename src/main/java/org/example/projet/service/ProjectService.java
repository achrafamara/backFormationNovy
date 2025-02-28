package org.example.projet.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projet.dao.ProjectDao;

import org.example.projet.entities.Project;
import org.example.projet.exceptions.DataNotFoundException;
import org.example.projet.model.Employee;
import org.example.projet.repositories.ProjectRepository;
import org.example.projet.restClients.EmployeeRestClient;
import org.example.projet.service.dtos.ProjectDto;
import org.example.projet.service.dtos.ProjectFilter;
import org.example.projet.service.mappers.ProjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectDao projectDao;
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final EmployeeRestClient employeeRestClient;

    public ProjectDto findById(Long id) {
        log.info("Getting project by id: {}", id);
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Project not found"));

        Employee employee = employeeRestClient.getEmployeeById(project.getEmployeeId());
        project.setEmployee(employee);

        return new ProjectDto(
                project.getId(),
                project.getProjectName(),
                project.getEmployeeId()
        );
    }


    @Transactional
    public ProjectDto save(ProjectDto projectDto) {
        log.info("Saving new project: {}", projectDto);

        Employee employee = employeeRestClient.getEmployeeById(projectDto.employeeId());
        if (employee == null) {
            throw new DataNotFoundException("Employee not found");
        }

        var project = projectMapper.toEntity(projectDto);
        project.setEmployeeId(employee.getId());

        return projectMapper.toDto(projectRepository.save(project));
    }

    @Transactional(readOnly = true)
    public Page<ProjectDto> findAll(ProjectFilter projectFilter) {
        return projectDao.findAll(projectFilter);
    }

    @Transactional
    public ProjectDto update(Long id, ProjectDto projectDto) {
        log.info("Updating project with id: {}", id);

        var project = projectRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Project not found"));

        Employee employee = employeeRestClient.getEmployeeById(projectDto.employeeId());
        if (employee == null) {
            throw new DataNotFoundException("Employee not found");
        }

        projectMapper.updateEntityFromDto(projectDto, project);
        project.setEmployeeId(employee.getId());

        return projectMapper.toDto(projectRepository.save(project));
    }

    public List<Project> findAllWithCategory(){

        return projectDao.getProjectRepository().findAllWithCategory();
    }
}
