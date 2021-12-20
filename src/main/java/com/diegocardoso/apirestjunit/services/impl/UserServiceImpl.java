package com.diegocardoso.apirestjunit.services.impl;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.repositories.UserRepository;
import com.diegocardoso.apirestjunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
