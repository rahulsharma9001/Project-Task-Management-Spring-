package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Task;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    @Cacheable(value = "tasks")
    public List<Task> getAllTask(){
        log.info("Fetching All Tasks from DATABASE");
        return taskRepository.findAll();
    }

    @Cacheable(value = "tasks" , key = "#id")
    public Task updateTask(Long id, Task taskDetails){
        log.info("Updating Task in DATABASE and updating cache for ID: {}", id);
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task is not present with ID : {}"));

        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setStatus(taskDetails.getStatus());
        existingTask.setPriority(taskDetails.getPriority());
        existingTask.setAssignedTo(taskDetails.getAssignedTo());

        return taskRepository.save(existingTask);
    }


    @Cacheable(value = "tasks")
    public List<Task> getTaskUsingFilter(String status, String priority){
        log.info("Fetching Task By Using Filters from Database : {}",status,priority);
        if(status != null && priority != null){
            return taskRepository.findByStatusAndPriority(status,priority);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        }else if(priority != null){
            return taskRepository.findByPriority(priority);
        }
        return taskRepository.findAll();
    }

    @Cacheable(value = "tasks" , key = "#id")
    public Task getTaskById(Long id) {
        log.info("Fetching Task from DATABASE for ID : {}",id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
    }


    @CacheEvict(value = "tasks" , key = "#id")
    public void deleteTaskById(Long id){
        log.info("Deleting Task from DATABASE and removing from cache for ID: {}", id);
        taskRepository.deleteById(id);
    }

}
