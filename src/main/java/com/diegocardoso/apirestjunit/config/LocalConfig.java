package com.diegocardoso.apirestjunit.config;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {


    @Autowired
    private UserRepository repository;

    @Bean
    public void startDb() {
        User u1 = new User(null, "Diego Cardoso", "diego@gmail.com", "123");
        User u2 = new User(null, "Saphyra Cardoso", "Saphyra@gmail.com", "456");
        repository.saveAll(List.of(u1, u2));

    }
}
