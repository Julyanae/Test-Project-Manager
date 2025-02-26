package com.example.projectmanager.service;

import com.example.projectmanager.model.Project;
import com.example.projectmanager.repository.ProjectRepository;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepo;
    public ProjectService(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }
    public Optional<Project> getProjectById(Long id) {
        return projectRepo.findById(id);
    }
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}
