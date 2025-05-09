package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.UserEntity;
import com.example.Repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepo ur;
    
    public UserEntity save(UserEntity reglist) {
        return ur.save(reglist);
    }

    public List<UserEntity> getuser() {
        return ur.findAll();
    }
    
    @Transactional
    public Optional<UserEntity> getUserById(int id) {
        return ur.findById(id);
    }

    public UserEntity updateUser(int id, UserEntity newUser) {
        Optional<UserEntity> optionalUser = ur.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setUsername(newUser.getUsername());
            existingUser.setContact(newUser.getContact());
            existingUser.setEmail(newUser.getEmail());
            return ur.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}