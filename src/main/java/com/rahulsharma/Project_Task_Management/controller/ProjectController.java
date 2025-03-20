package com.rahulsharma.Project_Task_Management.controller;

import com.rahulsharma.Project_Task_Management.entity.Project;
import com.rahulsharma.Project_Task_Management.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project){
        return projectService.CreateProject(project);
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectUsingId(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/getAll")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id , @RequestBody Project project){
        log.info("Received Request to UPDATE the Project with ID: {}", id);
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project DELETED Successfully.");
    }
}
