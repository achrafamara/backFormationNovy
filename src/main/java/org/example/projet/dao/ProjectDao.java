package org.example.projet.dao;




import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.projet.entities.QProject;
import org.example.projet.repositories.ProjectRepository;
import org.example.projet.service.dtos.ProjectDto;
import org.example.projet.service.dtos.ProjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@Getter
public class ProjectDao extends AbstractDao {

    @Autowired
    private ProjectRepository projectRepository;

    public Page<ProjectDto> findAll(ProjectFilter projectFilter) {
        QProject project = QProject.project;

        JPAQuery<ProjectDto> query = getFactory()
                .select(Projections.constructor(
                        ProjectDto.class,
                        project.id,
                        project.projectName,
                        project.employeeId
                ))
                .from(project);

        // Filtrer par nom de projet
        var projectName = projectFilter.getProjectName();
        if (StringUtils.hasText(projectName)) {
            query.where(project.projectName.containsIgnoreCase(projectName));
        }

        Pageable pageable = projectFilter.getPageable();
        long count = query.select(project.id.count()).fetchOne();

        List<ProjectDto> projectDtos = query
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(projectDtos, pageable, count);
    }
}
