package com.user.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	ArrayList<User> findAll();

	Optional<User> findAllByusername(String username);

}
