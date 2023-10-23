package com.grupoSuperman.app.persistence.entities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class AppUser implements UserDetails {


        @Id
        @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
        private long id;
        private String name;
        private String username;
        private String email;
        private String password;
        @Enumerated(EnumType.STRING)
        private UserRole UserRole;

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
