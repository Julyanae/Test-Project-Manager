package com.example.projectmanager.controller;

import com.example.projectmanager.model.Task;
import com.example.projectmanager.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Liste paginée des tâches
    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    // Filtrer les tâches par titre ou statut avec pagination
    @GetMapping("/search")
    public ResponseEntity<Page<Task>> searchTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String title,
            Pageable pageable) {
        if (status != null) {
            return ResponseEntity.ok(taskService.getTasksByStatus(status, pageable));
        } else if (title != null) {
            return ResponseEntity.ok(taskService.getTasksByTitle(title, pageable));
        } else {
            return ResponseEntity.ok(taskService.getAllTasks(pageable));
        }
    }
}
