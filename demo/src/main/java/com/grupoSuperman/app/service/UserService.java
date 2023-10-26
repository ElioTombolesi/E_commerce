package com.grupoSuperman.app.service;

import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

       /* public UserDetails loadUserById(Long id){

            return userRepository.findById(id).get();
        }
*/
       public UserDetails loadUserById(Long id) {
           Optional<AppUser> userDetailsOptional = userRepository.findById(id);

           if (userDetailsOptional.isPresent()) {
               return userDetailsOptional.get();
           } else {
               // Puedes lanzar una excepción o devolver un UserDetails por defecto o hacer lo que consideres apropiado en caso de que el usuario no sea encontrado.
               throw new UsernameNotFoundException("No se encontró un usuario con el ID proporcionado: " + id);
           }
       }

        public AppUser createUser(AppUser user){

            return userRepository.save(user);
        }

    public List<AppUser> listar(){

            return userRepository.findAll();
    }

   public void eliminar(Long id) {
        userRepository.deleteById(id);
    }

    public AppUser actualizar(AppUser appUser) {
        return userRepository.save(appUser);
    }
    }



