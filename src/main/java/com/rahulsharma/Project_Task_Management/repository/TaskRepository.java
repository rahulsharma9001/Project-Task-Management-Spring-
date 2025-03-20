package com.rahulsharma.Project_Task_Management.repository;

import com.rahulsharma.Project_Task_Management.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
    List<Task> findByPriority(String priority);
    List<Task> findByStatusAndPriority(String status, String priority);
    Optional<Task> findById(Long id);
    List<Task> findByProject_ProjectId(Long projectId);
}
