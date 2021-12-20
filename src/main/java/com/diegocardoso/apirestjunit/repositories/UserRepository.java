package com.diegocardoso.apirestjunit.repositories;

import com.diegocardoso.apirestjunit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
