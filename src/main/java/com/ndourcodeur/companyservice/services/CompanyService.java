package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.CompanyDto;
import com.ndourcodeur.companyservice.entities.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company addCompany(CompanyDto dto, long idUser);

    Company editCompany(CompanyDto dto, long idCompany);

    Company findCompany(long idCompany);

    void deleteCompany(long idCompany);

    List<Company> findCompanies();

    Company getCompanyByName(String name);

    Boolean existsByName(String name);

}
