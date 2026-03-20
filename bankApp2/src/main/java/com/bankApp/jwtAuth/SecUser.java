package com.bankApp.jwtAuth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bankApp.entities.Clerk;
import com.bankApp.entities.Manager;

public class SecUser implements UserDetails {
	private static final long serialVersionUID = 1L;
	private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    // For Manager
    public SecUser(Manager manager) {
        this.username = manager.getName(); // or name
        this.password = manager.getPassword();
        this.authorities = List.of(
                new SimpleGrantedAuthority("ROLE_MGR")
        );
    }

    // For Clerk
    public SecUser(Clerk clerk) {
        this.username = clerk.getName();
        this.password = clerk.getPassword();
        this.authorities = List.of(
                new SimpleGrantedAuthority("ROLE_CLERK")
        );
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // JWT apps usually just return true unless you implement these features

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
