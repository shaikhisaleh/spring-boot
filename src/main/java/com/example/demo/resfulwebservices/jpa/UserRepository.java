package com.example.demo.resfulwebservices.jpa;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
