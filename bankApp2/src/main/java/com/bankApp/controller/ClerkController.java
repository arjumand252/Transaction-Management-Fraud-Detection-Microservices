package com.bankApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.ClerkCreateRequest;
import com.bankApp.entities.Clerk;
import com.bankApp.service.ClerkService;

@RestController
@RequestMapping(path = "v1/Clerk")
public class ClerkController {
	private ClerkService clerkService;

	public ClerkController(ClerkService clerkService) {
		this.clerkService = clerkService;
	}
	@GetMapping()
	public List<Clerk> getAll(){
		return clerkService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public Clerk getById(@PathVariable int id ){
		return clerkService.getById(id);
	}
	
	@PostMapping(path = "addClerk")
	public void addClerk(@RequestBody ClerkCreateRequest clerkreq) {
//		System.out.println("add account controller method called");
		clerkService.addClerk(clerkreq);
	}
	
	@DeleteMapping(path = "{id}")
    public void deleteClerk(@PathVariable Integer id) {
		clerkService.deleteClerk(id);
    }
	
	@PutMapping(path = "updateClerk/{id}")
	public void updateManager(
	        @PathVariable Integer id,
	        @RequestBody Clerk clerk) {

	    clerk.setId(id);
	    clerkService.updateClerk(clerk);
	}
}
