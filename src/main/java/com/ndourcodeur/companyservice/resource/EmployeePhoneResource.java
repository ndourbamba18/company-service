package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.dto.EmployeePhoneDto;
import com.ndourcodeur.companyservice.entities.EmployeePhone;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.services.EmployeePhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/phones")
public class EmployeePhoneResource {

    private final EmployeePhoneService employeePhoneService;

    public EmployeePhoneResource(EmployeePhoneService employeePhoneService) {
        this.employeePhoneService = employeePhoneService;
    }

    @PostMapping(path = "/addPhone/{idEmployee}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveNewEmployeePhone(@Valid @RequestBody EmployeePhoneDto dto, @PathVariable Long idEmployee){
        EmployeePhone phone = employeePhoneService.addEmployeePhone(dto, idEmployee);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updatePhone/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEmployeePhone(@Valid @RequestBody EmployeePhoneDto dto, @PathVariable Long id){
        EmployeePhone phone = employeePhoneService.editEmployeePhone(dto, id);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deletePhone/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployeePhone(@PathVariable Long id){
        employeePhoneService.deleteEmployeePhone(id);
        return new ResponseEntity<>(new Message("Employee Phone deleted successfully with id: "+id), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll/{idEmployee}")
    public ResponseEntity<?> getAllEmployeePhones(@PathVariable Long idEmployee){
        List<EmployeePhone> employeePhones = employeePhoneService.findEmployeePhones(idEmployee);
        if (employeePhones.isEmpty())
            return new ResponseEntity<>(new Message("No Content"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(employeePhones, HttpStatus.OK);
    }

    @GetMapping(path = "/findOne/{id}")
    public ResponseEntity<?> fetchEmployeePhone(@PathVariable Long id){
        return new ResponseEntity<>(employeePhoneService.findEmployeePhone(id), HttpStatus.OK);
    }
}
