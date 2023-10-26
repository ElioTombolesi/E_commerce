package com.grupoSuperman.app.controller;


import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.persistence.repository.UserRepository;
import com.grupoSuperman.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {

        return ResponseEntity.ok(userService.createUser(user));

    }

    @GetMapping
    public List<AppUser> listar(){
        return userService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> buscar(@PathVariable Long id) {
        AppUser appUser = (AppUser) userService.loadUserById(id);

        return ResponseEntity.ok(appUser);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (userService.loadUserById(id) != null) {
            userService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

   /* @PutMapping()
    public ResponseEntity<AppUser> actualizar(@RequestBody AppUser appUser) {
        ResponseEntity<AppUser> response = null;

        if (appUser.getId() != null && userService.loadUserById(appUser.getId()).isPresent())
            response = ResponseEntity.ok(userService.actualizar(appUser));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }
*/
   @PutMapping()
   public ResponseEntity<AppUser> actualizar(@RequestBody AppUser appUser) {
       ResponseEntity<AppUser> response = null;

       UserDetails userDetails = userService.loadUserById(appUser.getId());
       if (userDetails != null) {
           response = ResponseEntity.ok(userService.actualizar(appUser));
       } else {
           response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       return response;
   }
}
