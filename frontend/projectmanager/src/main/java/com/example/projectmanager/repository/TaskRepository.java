package com.example.projectmanager.repository;

import com.example.projectmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(String status, Pageable pageable);
    Page<Task> findByTitleContaining(String title, Pageable pageable);
    Page<Task> findAll(Pageable pageable);
    List<Task> findByProject_Id(Long projectId);

}
