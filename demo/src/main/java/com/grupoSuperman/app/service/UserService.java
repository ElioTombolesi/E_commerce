package com.grupoSuperman.app.service;

import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class UserService implements UserDetailsService {


        @Autowired
        private final UserRepository userRepository;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String email){

            return userRepository.findByUsername(email).get();
        }


        public AppUser createUser(AppUser user){

            return userRepository.save(user);
        }
    }



