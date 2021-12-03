package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.dto.CompanyDto;
import com.ndourcodeur.companyservice.entities.Company;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.payload.response.MessageResponse;
import com.ndourcodeur.companyservice.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/companies")
public class CompanyResource {

    private final CompanyService companyService;

    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(path = "/addCompany/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveNewCompany(@Valid @RequestBody CompanyDto dto, @PathVariable Long idUser){
        if (companyService.existsByName(dto.getName()))
            return new ResponseEntity<>(new Message("Company Name already exists!"), HttpStatus.BAD_REQUEST);
        Company company = companyService.addCompany(dto, idUser);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateCompany/{idCompany}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCompanyById(@Valid @RequestBody CompanyDto dto, @PathVariable Long idCompany){
       /* if (companyService.existsByName(dto.getName()) && companyService.getCompanyByName(dto.getName()).getId().ge)
            return new ResponseEntity<>(new Message("Company Name already exists!"), HttpStatus.BAD_REQUEST);*/
        Company company = companyService.editCompany(dto, idCompany);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteCompany/{idCompany}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCompany(@PathVariable Long idCompany){
        companyService.deleteCompany(idCompany);
        return new ResponseEntity<>(new MessageResponse("Company successfully deleted with id:"+idCompany), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> getAllCompanies(){
        List<Company> companies = companyService.findCompanies();
        if (companies.isEmpty()){
            return new ResponseEntity<>(new Message("Sorry, No Content Here!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping(path = "/findCompany/{idCompany}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long idCompany){
        return new ResponseEntity<>(companyService.findCompany(idCompany), HttpStatus.OK);
    }

}
