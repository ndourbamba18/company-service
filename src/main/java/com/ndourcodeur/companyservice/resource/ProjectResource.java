package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.dto.ProjectDto;
import com.ndourcodeur.companyservice.entities.Project;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/projects")
public class ProjectResource {

    private final ProjectService projectService;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "/addProjectToEmployee/{idEmployee}/{idProject}")
    public ResponseEntity<?> saveNewProjectToEmployee(@PathVariable Long idProject,
                                                      @PathVariable Long idEmployee){
        Project project = projectService.addProjectToEmployee(idEmployee, idProject);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PostMapping(path = "/addProjectToDepartment/{idDepartment}")
    public ResponseEntity<?> saveNewProjectToDepartment(@Valid @RequestBody ProjectDto dto, @PathVariable Long idDepartment){
        Project project = projectService.addProjectToDepartment(dto, idDepartment);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateProject/{id}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDto dto, @PathVariable Long id){
        Project project = projectService.editProject(dto, id);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteProject/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(new Message("Project successfully deleted with ID: "+id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteProjectFromEmployee/{idEmployee}/{idProject}")
    public ResponseEntity<?> deleteProjectFromEmployee(@PathVariable Long idEmployee, @PathVariable Long idProject){
        projectService.deleteProjectFromEmployee(idEmployee, idProject);
        return new ResponseEntity<>(new Message("Project from idEmployee: "+idEmployee+" successfully deleted with ID: "+idProject), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> getAllProjects(){
        List<Project> projects = projectService.findProjects();
        if (projects.isEmpty())
            return new ResponseEntity<>(new Message("No Content"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(path = "/findProjectsForEmployee/{idEmployee}")
    public ResponseEntity<?> findProjectsForEmployee(@PathVariable Long idEmployee){
        List<Project> projects = projectService.findProjectsForEmployee(idEmployee);
        if (projects.isEmpty())
            return new ResponseEntity<>(new Message("No Content"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(path = "/findOne/{id}")
    public ResponseEntity<?> findProject(@PathVariable Long id){
        return new ResponseEntity<>(projectService.findProject(id), HttpStatus.OK);
    }
}
