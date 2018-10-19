package com.silalahi.valentinus.fintech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silalahi.valentinus.fintech.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

}
