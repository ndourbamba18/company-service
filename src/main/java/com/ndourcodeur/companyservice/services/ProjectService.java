package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.ProjectDto;
import com.ndourcodeur.companyservice.entities.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project addProjectToEmployee(Long idEmployee, Long idProject);
    Project addProjectToDepartment(ProjectDto dto, Long idDepartment);

    Project editProject(ProjectDto dto, Long id);
    Project findProject(Long id);

    void deleteProject(Long id);
    void deleteProjectFromEmployee(Long idEmployee, Long idProject);

    List<Project> findProjectsForEmployee(Long idEmployee);
    List<Project> findProjects();
}
