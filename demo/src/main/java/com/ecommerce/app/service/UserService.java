package com.ecommerce.app.service;

import com.ecommerce.app.entities.UserEntity;
import com.ecommerce.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String email){
            UserEntity user = userRepository.findByEmail(email);
            if (user == null){
                throw new UsernameNotFoundException(email);
            }
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
            UserDetails userDetails = new User(user.getEmail(), user.getPassword(), roles);
            return userDetails;
        }

        public UserEntity loadUserByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        public Boolean existsByEmail(String email){
            return userRepository.existsByEmail(email);
        };


       public Optional<UserEntity> loadUserById(Long id) {
           return userRepository.findById(id);
       }

        public UserDetails create(UserEntity user){
           var userSaved = userRepository.save(user);
           return loadUserByUsername(userSaved.getEmail());
        }

        public List<UserEntity> getAll(){
            return userRepository.findAll();
        }

        public void delete(Long id) {
            userRepository.deleteById(id);
        }
        public UserEntity update(UserEntity appUser) {
            return userRepository.save(appUser);
        }

    }



