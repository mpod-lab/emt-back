package net.emt.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.Company;
import net.emt.springboot.services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/")
	public List<Company> getAllCompanies() {
		return this.companyService.getAllCompanies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId) throws ResourceNotFoundException {
		return this.companyService.getCompanyById(companyId);
	}
	
	@PostMapping("/")
	public Company createCompany(@RequestBody Company company) {
		return this.companyService.saveCompany(company);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") Long companyId,
			@Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
		return this.companyService.updateCompany(companyId, companyDetails);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId) throws ResourceNotFoundException {
		return companyService.deleteCompany(companyId);
	}
}
