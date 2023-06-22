package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

@RestController
public class PatientRestController {
	@Autowired
	Repo repo;
	@Autowired
	providerrepo prepo;
	
	@GetMapping("/patient")
	public List<Patient> patientlist(){
		return repo.findAll();
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> showpatient(@PathVariable int id ) {
		
		
		Optional<Patient> optional_patient =repo.findById(id);
		
		if(optional_patient.isPresent()) {
			return ResponseEntity.ok(optional_patient.get());
		}
		else {
		
		return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping("/addpatient")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
	    List<Provider> providers = new ArrayList<>();
	    if (patient.getProviders() != null) {
	        for (Provider provider : patient.getProviders()) {
	            Provider existingProvider = prepo.findById(provider.getProviderId()).orElse(null);
	            if (existingProvider != null) {
	                providers.add(existingProvider);
	            }
	        }
	    }
	    
	    patient.setProviders(providers);
	    Patient savedPatient = repo.save(patient);

	    return ResponseEntity.ok(savedPatient);
	}
	
	@PutMapping("/updatepatient/{id}")
	public ResponseEntity<Patient> update(@PathVariable int id, @RequestBody Patient p) {
	    Optional<Patient> optionalPatient = repo.findById(id);
	    if (optionalPatient.isPresent()) {
	        Patient patient = optionalPatient.get();
	        patient.setName(p.getName());
	        patient.setEmail(p.getEmail());
	       // patient.setId(p.getId());
	        repo.save(patient);
	        return ResponseEntity.ok(patient);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/userdelete/{id}")
	public ResponseEntity<String> deletepatient(@PathVariable String id) {
		int del_id=Integer.parseInt(id);
		if(repo.findById(del_id).isPresent()) {
			repo.deleteById(del_id);
			return ResponseEntity.ok("user is deleted");
			
			
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID  was not found");	}
		
	}

}
