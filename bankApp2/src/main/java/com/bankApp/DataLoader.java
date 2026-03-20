package com.bankApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bankApp.dao.ManagerDao;
import com.bankApp.dto.ClerkCreateRequest;
import com.bankApp.entities.Manager;
import com.bankApp.entities.Clerk;
import com.bankApp.service.ClerkService;

@Component
public class DataLoader implements CommandLineRunner {

	    @Autowired
	    private ManagerDao managerDao;
	    
	    @Lazy
	    @Autowired
	    private ClerkService clerkService;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Override
	    public void run(String... args) throws Exception {
	        System.out.println("DataInitializer running...");
	            Manager admin = new Manager();
	            admin.setName("Admin");
	            admin.setEmail("admin@test.com");
	            admin.setPassword(passwordEncoder.encode("admin123"));
	            managerDao.addManager(admin);
	            System.out.println("Admin user created.");
	            
	         // ===== MANAGER 2 =====
	            Manager manager2 = new Manager();
	            manager2.setName("Admin Two");
	            manager2.setEmail("admin2@test.com");
	            manager2.setPassword(passwordEncoder.encode("admin456"));
	            managerDao.addManager(manager2);
	            
	            // Clerk c1 = new Clerk();
	            // c1.setName("Clerk One");
                // c1.setEmail("clerk1@test.com");
                // c1.setPassword(passwordEncoder.encode("clerk123"));
                // c1.setManager(admin);   // VERY IMPORTANT
                // clerkService.addClerk(c1);

	            ClerkCreateRequest c2 = new ClerkCreateRequest();
	            c2.clerkName = "Clerk Two";
	            c2.password = "clerk123";
	            c2.managerId = manager2.getId();

	            clerkService.addClerk(c2);

	            System.out.println("Clerks created.");

	            System.out.println("2 Managers and 2 Clerks created.");

	    }
	}


