package com.example.demo;
import java.io.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProviderRest {
	@Autowired
	providerrepo prepo;
	
	@GetMapping("/providers")
	public List<Provider> showdoctors(){
		return prepo.findAll();
		
	}
	
	@GetMapping("/provider/{id}")
	public ResponseEntity<Provider> getprovider(@PathVariable int id) throws IOException{
		//ObjectMapper om=new ObjectMapper();
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\saisiddartham\\Desktop\\eclipse files\\IOpractice\\reader.txt"));
							if(prepo.findById(id).isPresent()) {
								
					
					return ResponseEntity.ok(prepo.getById(id));
					
					
					
				}
				
				else {
					
				return ResponseEntity.notFound().build();
				}
			}
		
		
	
	
	@PostMapping("/addprovider")
	public Provider addprovider(@RequestBody Provider p){
	    Provider newProvider = new Provider(p.getName(), p.getSpec());
	    return prepo.save(newProvider);
	}

	
	@PutMapping("/updateprovider/{id}")
	public ResponseEntity<Provider> update(@PathVariable int id, @RequestBody Provider p1 ){
		Optional<Provider> op=prepo.findById(id);
		if(op.isPresent()) {
			Provider p2=op.get();
			p2.setName(p1.getName());
			p2.setSpec(p1.getSpec());
			//p2.setId(p1.getId());
			prepo.save(p2);
			return ResponseEntity.ok(p1);
			
			
		}
		else return ResponseEntity.notFound().build();
		
	}
	
	
	@DeleteMapping("/providerdelete/{id}")
	public ResponseEntity<String> deleteprovider(@PathVariable String id) {
		int del_id=Integer.parseInt(id);
		if(prepo.findById(del_id).isPresent()) {
			prepo.deleteById(del_id);
			return ResponseEntity.ok("user is deleted");
			
			
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID  was not found");	}
		
	}
	
	@GetMapping("/getemployeebyname/{name}")
	public List<Provider> getemployeebyname(@PathVariable("name")String name){
		
		return prepo.getProvidersByName(name);
		
		
	}
	@GetMapping("/getemployyebyspec/{spec}")
	public List<Provider> getemployeebyspec(@PathVariable("spec")String spec){
		Specification<Provider> spl=Specification.where(CriteriaApiimp.getSpec(spec));
		return prepo.findAll(spl);
		
	}
	
	//patient provider
	@GetMapping("/getpatientprovider/{name}")
	public List<Provider> getPatientProviders(@PathVariable("name") String patientName) {
	    Specification<Provider> spec = Specification.where(CriteriaApiimp.getProvidersByPatientName(patientName));
	  
	    return prepo.findAll(spec);
	}
	
	@GetMapping("/getbyidprovider/{id}")
	public List<Provider> getidprovider(@PathVariable("id") int id){
	Specification sp=Specification.where(CriteriaApiimp.getProviderById(id));
	return prepo.findAll(sp);
		
	}
	
	@PostMapping("/addproviderfromfile")
	public ResponseEntity<Provider> addProviderFromFile() throws IOException {
		Provider p2=new Provider();
		p2.setName("filedoc");
		p2.setSpec("filer");
		prepo.save(p2);
		BufferedWriter bf=new BufferedWriter(new FileWriter("C:\\Users\\saisiddartham\\Desktop\\eclipse files\\IOpractice\\Employee.txt"));
		bf.write(p2.getName());
		bf.write(p2.getSpec());
		System.out.println("writtern successfully");
		bf.close();
		
		return null;
	    

	    
	}


	

	

}
