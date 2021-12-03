package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.ProjectDto;
import com.ndourcodeur.companyservice.entities.Department;
import com.ndourcodeur.companyservice.entities.Employee;
import com.ndourcodeur.companyservice.entities.Project;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.DepartmentRepository;
import com.ndourcodeur.companyservice.repository.EmployeeRepository;
import com.ndourcodeur.companyservice.repository.ProjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Component
@Transactional
public class ProjectServiceImpl  implements ProjectService{

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository) {

        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Project addProjectToEmployee(Long idEmployee, Long idProject) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with ID:"+idEmployee));
        Project project = projectRepository.findById(idProject)
                .orElseThrow( () -> new ResourceNotFoundException("Project not found with ID: "+idProject));
        /*project.setId(dto.getId());
        project.setName(dto.getName());
        project.setLocation(dto.getLocation());
        project.setDescription(dto.getDescription());
        project.setCreatedAt(new Date());*/
        //project.setProcess(dto.getProcess());
        employee.addProjectToEmployee(project);
        return projectRepository.save(project);
    }

    @Override
    public Project addProjectToDepartment(ProjectDto dto, Long idDepartment) {
        Department department = departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new ResourceNotFoundException("Department not found with ID:"+idDepartment));
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setLocation(dto.getLocation());
        project.setDescription(dto.getDescription());
        project.setCreatedAt(new Date());
        //project.setProcess(dto.getProcess());
        department.addProjectToDepartment(project);
        return projectRepository.save(project);
    }

    @Override
    public Project editProject(ProjectDto dto, Long id) {
        Project existsProject = projectRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Project not found with ID: "+id));
        existsProject.setId(dto.getId());
        existsProject.setName(dto.getName());
        existsProject.setLocation(dto.getLocation());
        existsProject.setDescription(dto.getDescription());
        return projectRepository.save(existsProject);
    }

    @Override
    public Project findProject(Long id) {
        Project existsProject = projectRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Project not found with ID: "+id));
        return existsProject;
    }

    @Override
    public void deleteProject(Long id) {
        Project existsProject = projectRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Project not found with ID: "+id));
        projectRepository.delete(existsProject);
    }

    @Override
    public void deleteProjectFromEmployee(Long idEmployee, Long idProject) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with ID: "+idEmployee));
        Project project = projectRepository.findById(idProject)
                .orElseThrow( () -> new ResourceNotFoundException("Project not found with ID: "+idProject));
        employee.getProjects().remove(project);
    }

    @Override
    public List<Project> findProjectsForEmployee(Long idEmployee) {
        Employee existsEmployee = employeeRepository.findById(idEmployee)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with ID: "+idEmployee));
        return existsEmployee.getProjects();
    }

    @Override
    public List<Project> findProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }
}
