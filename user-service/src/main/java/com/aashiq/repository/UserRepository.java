package com.aashiq.repository;

import com.aashiq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


    User findByUsernameAndPassword(String tempUsername, String tempPassword);
}
