package com.example.projectmanager.service;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.model.Task;
import com.example.projectmanager.repository.ProjectRepository;
import com.example.projectmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepo;
    private final TaskRepository taskRepo;

    public ProjectService(ProjectRepository projectRepo, TaskRepository taskRepo) {
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepo.findById(id);
    }

    @Transactional
    public Project saveProject(Project project) {
        if (project.getTasks() != null) {
            for (Task task : project.getTasks()) {
                task.setProject(project);
            }
        }
        return projectRepo.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        taskRepo.deleteAll(taskRepo.findByProject_Id(id));
        projectRepo.deleteById(id);
    }
}
