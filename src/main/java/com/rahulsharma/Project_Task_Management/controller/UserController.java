package com.rahulsharma.Project_Task_Management.controller;

import com.rahulsharma.Project_Task_Management.entity.User;
import com.rahulsharma.Project_Task_Management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public List<User> createUser(@RequestBody User user){
        log.info("Received request to Create a User");
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        log.info("Received Request to Get User using ID : {}",id);
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUsers(){
        log.info("Received request to Get All Users");
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id , @RequestBody User user){
        log.info("Received Request to UPDATE the User with ID: {}", id);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info("Received request to Delete a User with ID : {}",id);
        userService.deleteUser(id);
        return ResponseEntity.ok("User DELETED successfully.");
    }
}
