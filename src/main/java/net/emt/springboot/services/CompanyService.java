package net.emt.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.Company;
import net.emt.springboot.repository.CompanyRepository;

@Component
public class CompanyService {
	
	@Autowired CompanyRepository companyRepository;

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
	
	public ResponseEntity<Company> getCompanyById(Long id) throws ResourceNotFoundException {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id ::" + id));	
		return ResponseEntity.ok().body(company);
	}
	
	public Company saveCompany(Company company) {
		return this.companyRepository.save(company);
	}
	
	public ResponseEntity<Company> updateCompany(Long companyId, Company companyDetails) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		
		company.setCompanyName(companyDetails.getCompanyName());
		
		return ResponseEntity.ok(this.companyRepository.save(company));
	}
	
	public Map<String, Boolean> deleteCompany(Long companyId) throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		
		this.companyRepository.delete(company);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
