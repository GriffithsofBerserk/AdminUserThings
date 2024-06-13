package com.example.AdminUserThings.services;


import com.example.AdminUserThings.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    public List<User> findByEmail(String email);
}
