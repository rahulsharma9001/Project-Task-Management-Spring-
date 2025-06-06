package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Project;
import com.rahulsharma.Project_Task_Management.entity.User;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired(required = true)
    private final UserRepository userRepository;
    
    public User saveUser(User user){
        return userRepository.save(user);
    }

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        log.info("Fetching ALL users from DATABASE");
        return userRepository.findAll();
    }

    @Cacheable(value = "users" , key = "#id")
    public Optional<User> getUserById(Long id) {
        log.info("Fetching User from DATABASE for ID : {}",id);
        return userRepository.findById(id);
    }

    @CacheEvict(value = "users")
    public void deleteUser(Long id) {
        log.info("Deleting user from DATABASE and removing from cache for ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }

    @CachePut(value = "users" , key = "#id")
    public User updateUser(Long id, User userDetails){
        log.info("Updating user in DATABASE and updating cache for ID: {}", id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setActive(userDetails.getActive());
        existingUser.setRole(userDetails.getRole());
        existingUser.setEmail(userDetails.getEmail());
        return userRepository.save(existingUser);
    }




}
