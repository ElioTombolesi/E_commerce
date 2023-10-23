package com.grupoSuperman.app.controller;


import com.grupoSuperman.app.persistence.entities.AppUser;
import com.grupoSuperman.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {

        return ResponseEntity.ok(userService.createUser(user));

    }


}
