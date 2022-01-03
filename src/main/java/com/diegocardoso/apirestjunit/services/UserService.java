package com.diegocardoso.apirestjunit.services;

import com.diegocardoso.apirestjunit.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

}
