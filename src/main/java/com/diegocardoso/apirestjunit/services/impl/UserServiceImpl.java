package com.diegocardoso.apirestjunit.services.impl;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.domain.userDTO.UserDTO;
import com.diegocardoso.apirestjunit.repositories.UserRepository;
import com.diegocardoso.apirestjunit.services.UserService;
import com.diegocardoso.apirestjunit.services.exeptions.DataIntegrateViolationExeption;
import com.diegocardoso.apirestjunit.services.exeptions.ObjectNotFoundExeption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExeption("usuario não encontrado..."));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }


    //metodo para verificar se email já existe
    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());

        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegrateViolationExeption("email já cadastrado!");

        }


    }
}
