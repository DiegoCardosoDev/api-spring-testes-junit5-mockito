package com.diegocardoso.apirestjunit.resource;


import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));


    }


}
