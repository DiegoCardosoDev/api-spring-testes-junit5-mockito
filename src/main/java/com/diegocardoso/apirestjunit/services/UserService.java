package com.diegocardoso.apirestjunit.services;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.domain.userDTO.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO obj);

    User update(UserDTO obj);

}
