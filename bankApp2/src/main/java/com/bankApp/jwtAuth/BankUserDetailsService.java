package com.bankApp.jwtAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankApp.dao.ClerkDao;
import com.bankApp.dao.ManagerDao;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Manager;

@Service
public class BankUserDetailsService implements UserDetailsService {

    @Autowired
    private ManagerDao managerRepo;

    @Autowired
    private ClerkDao clerkRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Manager manager = managerRepo.getByUserName(username);
        if (manager != null) {
            return new SecUser(
                   manager
            );
        }

        Clerk clerk = clerkRepo.getByUserName(username);
        if (clerk != null) {
            return new SecUser(
                    clerk
            );
        }

        throw new UsernameNotFoundException("User not found");
    }
}
