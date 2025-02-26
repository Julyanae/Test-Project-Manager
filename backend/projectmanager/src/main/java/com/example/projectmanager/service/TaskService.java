package com.example.projectmanager.service;

import com.example.projectmanager.model.Task;
import com.example.projectmanager.model.TaskStatus;
import com.example.projectmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Page<Task> getTasksByTitle(String title, Pageable pageable) {
        return taskRepo.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Task> getTasksByStatus(String status, Pageable pageable) {
        TaskStatus taskStatus;
        try {
            taskStatus = TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Statut invalide : " + status);
        }
        return taskRepo.findByStatus(taskStatus, pageable);
    }


    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }

    public Page<Task> getTasksByProjectId(Long projectId, Pageable pageable) {
        return taskRepo.findByProject_Id(projectId, pageable);
    }

    public List<Task> getTasksByProjectId(Long projectId) { // Ajout pour les recherches sans pagination
        return taskRepo.findByProject_Id(projectId);
    }
}
