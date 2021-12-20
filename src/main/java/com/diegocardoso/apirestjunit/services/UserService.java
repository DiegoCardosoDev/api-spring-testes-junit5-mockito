package com.diegocardoso.apirestjunit.services;

import com.diegocardoso.apirestjunit.domain.User;

public interface UserService {

    User findById(Integer id);
}
