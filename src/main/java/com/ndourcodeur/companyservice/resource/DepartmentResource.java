package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.dto.DepartmentDto;
import com.ndourcodeur.companyservice.entities.Department;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/departments")
public class DepartmentResource {

    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/addDepartment/{idCompany}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveNewDepartment(@Valid @RequestBody DepartmentDto dto, @PathVariable Long idCompany){
        Department apt = departmentService.addDepartment(dto, idCompany);
        return new ResponseEntity<>(apt, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateDepartment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody DepartmentDto dto, @PathVariable Long id){
        Department dpt = departmentService.editDepartment(dto, id);
        return new ResponseEntity<>(dpt, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteDepartment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(new Message("Department successfully deleted with id: "+id), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> getAllDepartments(){
        List<Department> departments = departmentService.findDepartments();
        if (departments.isEmpty())
            return new ResponseEntity<>(new Message("Sorry, No Content Here."), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(path = "/findOne/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.findDepartment(id), HttpStatus.OK);
    }
}
