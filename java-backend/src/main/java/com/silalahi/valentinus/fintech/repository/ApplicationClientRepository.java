package com.silalahi.valentinus.fintech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silalahi.valentinus.fintech.entity.ApplicationClient;

public interface ApplicationClientRepository extends JpaRepository<ApplicationClientRepository, String> {

	Optional<ApplicationClient> findByName(String name);

}
