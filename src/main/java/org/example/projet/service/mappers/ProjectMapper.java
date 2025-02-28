package org.example.projet.service.mappers;


import org.example.projet.entities.Project;
import org.example.projet.service.dtos.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectDto projectDto);
    ProjectDto toDto(Project project);

    void updateEntityFromDto(ProjectDto projectDto, @MappingTarget Project project);
}

