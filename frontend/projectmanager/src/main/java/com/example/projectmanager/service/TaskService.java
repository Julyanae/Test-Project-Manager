package com.example.projectmanager.service;

import com.example.projectmanager.model.Task;
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
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepo.findByProject_Id(projectId);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }

    public Page<Task> getTasksByStatus(String status, Pageable pageable) {
        return taskRepo.findByStatus(status, pageable);
    }

    public Page<Task> getTasksByTitle(String title, Pageable pageable) {
        return taskRepo.findByTitleContaining(title, pageable);
    }
}
