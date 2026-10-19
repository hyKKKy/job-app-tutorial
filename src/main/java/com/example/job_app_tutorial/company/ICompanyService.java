package com.example.job_app_tutorial.company;

import java.util.List;

public interface ICompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    boolean updateCompany(Long id, Company company);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    Company findOneByName(String name);
}
