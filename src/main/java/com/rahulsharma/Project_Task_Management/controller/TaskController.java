package com.rahulsharma.Project_Task_Management.controller;

import com.rahulsharma.Project_Task_Management.entity.Task;
import com.rahulsharma.Project_Task_Management.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public List<Task> createTask(@RequestBody Task task){
        log.info("Received request to Create Task");
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        log.info("Received request to Get Task with ID : {}",id);
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/getAllTask")
    public List<Task> getAllTasks(){
        log.info("Received request to Get All Tasks");
        return taskService.getAllTask();
    }

    @GetMapping("/filter")
    public List<Task> searchTaskByFilters(@RequestParam(required = false) String status, @RequestParam(required = false) String priority){
        log.info("Received request to Search Tasks Using Filters");
        List<Task> tasks = taskService.getTaskUsingFilter(status,priority);
        return taskService.getTaskUsingFilter(status,priority);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id , @RequestBody Task task){
        log.info("Received Request to UPDATE the Task with ID: {}", id);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        log.info("Received request to Delete the Task with ID ; {}",id);
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Task DELETED Successfully");
    }
}
