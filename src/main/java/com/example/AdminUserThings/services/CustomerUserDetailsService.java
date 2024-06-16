package com.example.AdminUserThings.services;

import com.example.AdminUserThings.models.CustomerUserDetails;
import com.example.AdminUserThings.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("No user found for the given email");
        }

        return new CustomerUserDetails(user);
    }
}
