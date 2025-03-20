package com.rahulsharma.Project_Task_Management.service;

import com.rahulsharma.Project_Task_Management.entity.Task;
import com.rahulsharma.Project_Task_Management.exception.ResourceNotFoundException;
import com.rahulsharma.Project_Task_Management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task taskDetails){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task is not present with ID : {}"));

        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setStatus(taskDetails.getStatus());
        existingTask.setPriority(taskDetails.getPriority());
        existingTask.setAssignedTo(taskDetails.getAssignedTo());

        return taskRepository.save(existingTask);
    }


    public List<Task> getTaskUsingFilter(String status, String priority){
        if(status != null && priority != null){
            return taskRepository.findByStatusAndPriority(status,priority);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        }else if(priority != null){
            return taskRepository.findByPriority(priority);
        }
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
    }


//    public Task updateTaskStatus(Long id, String status){
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task is not present with ID : {}"));
//        task.setStatus(status);
//        return taskRepository.save(task);
//    }

//    public Task updateTasks(Long id, Task taskDetails) {
//        Task existingTask = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
//
//        // Update task fields
//        existingTask.setTitle(taskDetails.getTitle());
//        existingTask.setStatus(taskDetails.getStatus());
//        existingTask.setPriority(taskDetails.getPriority());
//
//        return taskRepository.save(existingTask);
//    }


    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

}
