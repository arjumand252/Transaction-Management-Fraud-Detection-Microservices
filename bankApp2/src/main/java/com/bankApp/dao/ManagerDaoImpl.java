package com.bankApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.bankApp.dto.ClerkDTO;
import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Manager getById(Integer id) {
		Manager manager = em.find(Manager.class, id);
		if(manager == null) {
			throw new BankEmployeeNotFoundException("Manager with this id= "+id+" doesnt exist.");
		}
		return manager;
	}
	
	@Override
	public Manager getByUserName(String username) {

	    String jpql = "SELECT m FROM Manager m WHERE m.name = :username";

	    try {
	        return em.createQuery(jpql, Manager.class)
	                .setParameter("username", username)
	                .getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}


	@Override
	@EntityGraph(attributePaths = {"clerks", "accounts"})
//	public List<Manager> getAll() {
//		return em.createQuery("select m from Manager m", Manager.class).getResultList();
//	}
	public List<ManagerClerkDTO> getAll() {
	    List<Manager> managers = em.createQuery("SELECT m FROM Manager m LEFT JOIN FETCH m.clerks", Manager.class)
	                               .getResultList();

	    // Map to DTOs
	    return managers.stream()
	        .map(m -> new ManagerClerkDTO(
	            m.getId(),
	            m.getName(),
	            m.getClerks().stream()
	                .map(c -> new ClerkDTO(c.getId(), c.getName()))
	                .toList()
	        ))
	        .toList();
	}


	@Override
	public void addManager(Manager manager) {
		try {
			em.persist(manager);
		} catch(PersistenceException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteManager(Integer id) {
		Manager manager = getById(id);
		em.remove(manager);
	}

	@Override
	public void updateManager(Manager manager) {
//		try{
//			if(manager == null) {
//				throw new IllegalArgumentException("Manager or manager Id is not found.");
//			}
//			em.merge(manager);
//		}
//		catch(IllegalArgumentException ex) {
//			throw new BankEmployeeNotFoundException("Manager not found");
//		}
		Manager existing = em.find(Manager.class, manager.getId());

	    if (existing == null) {
	        throw new BankEmployeeNotFoundException("Manager not found");
	    }

	    if (manager.getName() != null)
	        existing.setName(manager.getName());

	    if (manager.getEmail() != null)
	        existing.setEmail(manager.getEmail());
	}

//	@Override
//	public Optional<Manager> findByManagerEmail(String username) {
//		Manager manager = em.find(Manager.class, username);
//		if(manager == null) {
//			throw new BankEmployeeNotFoundException("Manager with this name= "+username+" doesnt exist.");
//		}
//		return Optional.of(manager);
//	}
	@Override
	public Optional<Manager> findByManagerEmail(String email) {

	    TypedQuery<Manager> query = em.createQuery(
	        "SELECT m FROM Manager m WHERE m.managerEmail = :email",
	        Manager.class
	    );

	    query.setParameter("email", email);

	    List<Manager> result = query.getResultList();

	    if (result.isEmpty()) {
	        throw new BankEmployeeNotFoundException(
	            "Manager with email " + email + " doesn't exist."
	        );
	    }

	    return Optional.of(result.get(0));
	}


}
