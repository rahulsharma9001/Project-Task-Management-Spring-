package com.rahulsharma.Project_Task_Management.repository;

import com.rahulsharma.Project_Task_Management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
