package com.example.demo;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.Criteria;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;



@Component
@Repository
public interface providerrepo extends JpaRepository<Provider, Integer>, CriteriaApiInterface, JpaSpecificationExecutor<Provider>{
	
	
	
	

}
