package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.dto.EmployeeDto;
import com.ndourcodeur.companyservice.entities.Employee;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/employees")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/addEmployee/{idDepartment}")
    public ResponseEntity<?> saveNewEmployee(@Valid @RequestBody EmployeeDto dto, @PathVariable Long idDepartment){
        Employee employee = employeeService.addEmployee(dto, idDepartment);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto dto, @PathVariable Long id){
        Employee employee = employeeService.editEmployee(dto, id);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new Message("Employee successfully deleted with id :"+id), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> employees = employeeService.findEmployees();
        if (employees.isEmpty())
            return new ResponseEntity<>(new Message("No content here"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "/findOne/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.findEmployee(id), HttpStatus.OK);
    }
}
