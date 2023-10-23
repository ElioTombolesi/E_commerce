package com.grupoSuperman.app.persistence.repository;

import com.grupoSuperman.app.persistence.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



        @Repository
        @Transactional(readOnly = true)
        public interface UserRepository extends JpaRepository<AppUser, Long> {

            Optional<AppUser> findByUsername(String username);

        }


