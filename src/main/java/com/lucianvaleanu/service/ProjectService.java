package com.lucianvaleanu.service;

import com.lucianvaleanu.model.Project;
import com.lucianvaleanu.repository.ProjectItemRepository;
import com.lucianvaleanu.repository.ProjectRepository;
import com.lucianvaleanu.utils.dto.ProjectItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectItemRepository projectItemRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectItemRepository projectItemRepository) {
        this.projectRepository = projectRepository;
        this.projectItemRepository = projectItemRepository;
    }

    public List<Project> getAllProjectsByUserId(int userId) {
        return projectRepository.findByUserId(userId);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    public List<ProjectItemDTO> getProjectItemsList(int projectId) {
        return projectItemRepository.findItemsByProjectId(projectId);
    }
}