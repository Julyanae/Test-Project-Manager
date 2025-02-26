package com.example.projectmanager.repository;

import com.example.projectmanager.model.Task;
import com.example.projectmanager.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Task> findByProject_Id(Long projectId, Pageable pageable);
    List<Task> findByProject_Id(Long projectId);
}
