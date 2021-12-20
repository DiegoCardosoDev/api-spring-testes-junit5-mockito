package com.diegocardoso.apirestjunit.resource;


import com.diegocardoso.apirestjunit.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new User(1, "diego", "diego@cardoso", "123"));

    }


}
