package com.grupoSuperman.app.persistence.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class AppUser implements UserDetails {


        @Id
        @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
        private  Integer id;
        private String name;
        private String username;
        private String email;

        public  long getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public com.grupoSuperman.app.persistence.entities.UserRole getUserRole() {
                return UserRole;
        }

        public void setUserRole(com.grupoSuperman.app.persistence.entities.UserRole userRole) {
                UserRole = userRole;
        }

        private String password;
        @Enumerated(EnumType.STRING)
        private UserRole UserRole;

        @JsonIgnore
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
        }

        @Override
        public String getPassword() {
                return null;
        }

        @Override
        public String getUsername() {
                return null;
        }

        @Override
        public boolean isAccountNonExpired() {
                return false;
        }

        @Override
        public boolean isAccountNonLocked() {
                return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return false;
        }

        @Override
        public boolean isEnabled() {
                return false;
        }
}
