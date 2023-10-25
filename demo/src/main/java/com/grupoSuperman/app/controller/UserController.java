package com.grupoSuperman.app.controller;


import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AppUser> buscar(@PathVariable long id) {
        AppUser appUser = (AppUser) userService.loadUserById(id);

        return ResponseEntity.ok(appUser);
    }







}
