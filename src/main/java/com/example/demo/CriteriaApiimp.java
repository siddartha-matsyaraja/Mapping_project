package com.example.demo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import javax.persistence.PersistenceContext;

@Component
public class CriteriaApiimp implements CriteriaApiInterface {
    @PersistenceContext
    private EntityManager em;

    @Override
	public List<Provider> getProvidersByName(String name){
        jakarta.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Provider> cq = cb.createQuery(Provider.class);
        Root<Provider> rt = cq.from(Provider.class);

        Predicate p1 = cb.equal(rt.get("name"), name);
        cq.where(p1);

        TypedQuery<Provider> query = em.createQuery(cq);

        return query.getResultList();
    }
    public static Specification<Provider>getSpec(String spec){
		return (root, query, criteriaBuilder) ->{
			return criteriaBuilder.equal(root.get("spec"), spec);
		};
		
		
		
	}
    public static Specification<Provider> getProvidersByPatientName(String patientName) {
        return (root, query, criteriaBuilder) -> {
            Join<Provider, Patient> patientJoin = root.join("patient");
            return criteriaBuilder.equal(patientJoin.get("name"), patientName);
        };
    }
    
    public static Specification<Provider> getProviderById(int id){
    	return (root, query, criteriaBuilder) -> {
    		return criteriaBuilder.equal(root.get("id"), id);
    	};
    }

	
}
