package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Project;
import com.rahulsharma.Project_Task_Management.entity.User;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.ProjectRepository;
import com.rahulsharma.Project_Task_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Project CreateProject(Project project) {
        User user = userRepository.findById(project.getCreatedBy().getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + project.getCreatedBy().getUserId()));

        project.setCreatedBy(user);

        return projectRepository.save(project);
    }
    public Project updateProject(Long id, Project projectDetails) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        existingProject.setName(projectDetails.getName());
        existingProject.setDescription(projectDetails.getDescription());
        existingProject.setStartDate(projectDetails.getStartDate());
        existingProject.setEndDate(projectDetails.getEndDate());

        return projectRepository.save(existingProject);
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID must not be null");
        }
        return projectRepository.findById(id);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        projectRepository.deleteById(id);
    }
}