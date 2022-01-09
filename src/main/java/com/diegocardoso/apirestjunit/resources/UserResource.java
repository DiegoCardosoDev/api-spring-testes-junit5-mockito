package com.diegocardoso.apirestjunit.resources;



import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.domain.userDTO.UserDTO;
import com.diegocardoso.apirestjunit.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    //metodo para retornar um user
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    //metodo para retornar todos user
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));

    }

    //metodo para criar um user
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(service.create(obj)
                        .getId()).toUri();
        return ResponseEntity.created(uri).build();

    }


    //metodo para atulizar user
    @PutMapping(value = "/{id}  ")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj) {
        obj.setId(id);
        User newObj = service.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj, UserDTO.class));

    }


}
