package com.ndourcodeur.companyservice.services;
import com.ndourcodeur.companyservice.dto.CompanyDto;
import com.ndourcodeur.companyservice.entities.Company;
import com.ndourcodeur.companyservice.entities.User;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.CompanyRepository;
import com.ndourcodeur.companyservice.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Company addCompany(CompanyDto dto, long idUser) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setCreatedAt(new Date());
        company.setDescription(dto.getDescription());
        company.setLocation(dto.getLocation());
        User user = userRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with id:"+idUser));
        company.setUser(user);
        user.addCompanyToUser(company);
        return companyRepository.save(company);
    }

    @Override
    public Company editCompany(CompanyDto dto, long idCompany) {
        Company existsCompany = companyRepository.findById(idCompany)
                .orElseThrow( () -> new ResourceNotFoundException("Company not found with id: "+idCompany));
        existsCompany.setId(dto.getId());
        existsCompany.setName(dto.getName());
        existsCompany.setDescription(dto.getDescription());
        existsCompany.setLocation(dto.getLocation());
        return companyRepository.save(existsCompany);
    }

    @Override
    public Company findCompany(long idCompany) {
        Company existsCompany = companyRepository.findById(idCompany)
                .orElseThrow( () -> new ResourceNotFoundException("Company not found with id: "+idCompany));
        return existsCompany;
    }

    @Override
    public void deleteCompany(long idCompany) {
        Company existsCompany = companyRepository.findById(idCompany)
                .orElseThrow( () -> new ResourceNotFoundException("Company not found with id: "+idCompany));
        companyRepository.delete(existsCompany);
    }

    @Override
    public List<Company> findCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @Override
    public Company getCompanyByName(String name) {
        return this.companyRepository.findByName(name);
    }

    @Override
    public Boolean existsByName(String name) {
        return this.companyRepository.existsByName(name);
    }
}
