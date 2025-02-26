package com.example.projectmanager.controller;

import com.example.projectmanager.model.Task;
import com.example.projectmanager.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskService.getAllTasks(pageable);
    }


    @GetMapping("/search")
    public Page<Task> searchTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String title,
            Pageable pageable) {
        if (status != null) {
            return taskService.getTasksByStatus(status, pageable);
        } else if (title != null) {
            return taskService.getTasksByTitle(title, pageable);
        } else {
            return taskService.getAllTasks(pageable);
        }
    }
}
