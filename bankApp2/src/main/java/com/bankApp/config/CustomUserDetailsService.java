//package com.bankApp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.bankApp.entities.Clerk;
//import com.bankApp.entities.Manager;
//import com.bankApp.service.ClerkService;
//import com.bankApp.service.ManagerService;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private ManagerService managerService;
//
//    @Autowired
//    private ClerkService clerkService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        // Try Manager first
//        Manager manager = managerService.getByUserName(username);
//        System.out.println("Manager found: " + manager);
//        if (manager != null) {
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(manager.getName())
//                    .password(manager.getPassword()) // already hashed
//                    .roles("MANAGER")
//                    .build();
//        }
//
//        // Try Clerk
//        Clerk clerk = clerkService.getByUserName(username);
//        System.out.println("Clerk found: " + clerk);
//        if (clerk != null) {
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(clerk.getName())
//                    .password(clerk.getPassword())
//                    .roles("CLERK")
//                    .build();
//        }
//
//        throw new UsernameNotFoundException("User not found: " + username);
//    }
//}
