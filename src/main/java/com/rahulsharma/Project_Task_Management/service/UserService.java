package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Project;
import com.rahulsharma.Project_Task_Management.entity.User;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userDetails){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setActive(userDetails.getActive());
        existingUser.setRole(userDetails.getRole());
        existingUser.setEmail(userDetails.getEmail());
//        existingUser.setUserId(userDetails.getUserId());

        return userRepository.save(existingUser);
    }




}
