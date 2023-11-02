package com.ecommerce.app.controller;


import com.ecommerce.app.entities.UserEntity;
import com.ecommerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> GetAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.loadUserById(id));
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (userService.loadUserById(id) != null) {
            userService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

   @PutMapping()
   public ResponseEntity<UserEntity> Update(@RequestBody UserEntity user) {
       ResponseEntity<UserEntity> response = null;

       Optional<UserEntity> userDetails = userService.loadUserById(user.getId());
       if (userDetails != null) {
           response = ResponseEntity.ok(userService.update(user));
       } else {
           response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       return response;
   }
}
