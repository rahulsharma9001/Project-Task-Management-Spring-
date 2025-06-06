package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Project;
import com.rahulsharma.Project_Task_Management.entity.User;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.ProjectRepository;
import com.rahulsharma.Project_Task_Management.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @CachePut(value = "projects" , key = "'allProjects'")
    public Project CreateProject(Project project) {
        User user = userRepository.findById(project.getCreatedBy().getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + project.getCreatedBy().getUserId()));

        project.setCreatedBy(user);

        return projectRepository.save(project);
    }

    @CachePut(value = "projects" , key = "'existingProject.id'")
    public Project updateProject(Long id, Project projectDetails) {
        log.info("Updating Project in DATABASE and updating cache for ID: {}", id);
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        existingProject.setName(projectDetails.getName());
        existingProject.setDescription(projectDetails.getDescription());
        existingProject.setStartDate(projectDetails.getStartDate());
        existingProject.setEndDate(projectDetails.getEndDate());

        return projectRepository.save(existingProject);
    }


    @CacheEvict(value = "projects", key = "'allProjects'")
    public List<Project> getAllProjects() {
        log.info("Fetching All Projects from DATABASE");
        return projectRepository.findAll();
    }

    @Cacheable(value = "projects" , key = "#id")
    @Transactional
    public Optional<Project> getProjectById(Long id) {
        log.info("Fetching Project from DATABASE for ID : {}",id);
        if (id == null) {
            throw new IllegalArgumentException("Project ID must not be null");
        }
        return projectRepository.findById(id);
    }

    @CacheEvict(value = "projects", key = "#id")
    public void deleteProject(Long id) {
        log.info("Deleting Project from DATABASE and removing from cache for ID: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        projectRepository.deleteById(id);
    }
}