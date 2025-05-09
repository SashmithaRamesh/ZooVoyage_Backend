package com.example.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.UserEntity;
import com.example.Service.UserService;


@RestController
public class UserController {
    
    @Autowired
    UserService us;
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get")
    public List<UserEntity> getdata() {
        return us.getuser();
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/insert")
    public UserEntity insert(@RequestBody UserEntity reglist) {
        return us.save(reglist);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {
        Optional<UserEntity> user = us.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @RequestBody UserEntity user) {
        UserEntity updatedUser = us.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}