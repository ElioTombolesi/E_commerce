package com.grupoSuperman.app.service;

import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


        @Autowired
        private static UserRepository userRepository = null;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String email){

            return userRepository.findByUsername(email).get();
        }

        public UserDetails loadUserById(long id){

            return userRepository.findById(id).get();
        }


        public AppUser createUser(AppUser user){

            return userRepository.save(user);
        }

    public List<AppUser> listar(){

            return userRepository.findAll();
    }




    }



