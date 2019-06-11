package com.account.repository;

import org.springframework.data.repository.CrudRepository;

import com.account.entity.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}