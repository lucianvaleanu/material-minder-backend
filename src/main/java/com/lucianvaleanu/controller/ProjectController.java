package com.lucianvaleanu.controller;

import com.lucianvaleanu.model.Project;
import com.lucianvaleanu.service.ProjectService;
import com.lucianvaleanu.utils.dto.ProjectDTO;
import com.lucianvaleanu.utils.dto.ProjectItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDTO>> getAllProjectsByUserId(@PathVariable int userId) {
        List<ProjectDTO> projects = projectService.getAllProjectsByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable int projectId, @RequestBody ProjectDTO projectDTO) {
        // Check if project exists for the user
        if (!projectService.getAllProjectsByUserId(projectDTO.userId()).stream()
                .anyMatch(p -> p.getId().equals(projectId))) {
            return ResponseEntity.notFound().build();
        }
        Project project = toEntity(projectDTO);
        project.setId(projectId);
        projectService.updateProject(project);
        return ResponseEntity.ok(toDTO(project));
    }

    @GetMapping("/{projectId}/items")
    public ResponseEntity<List<ProjectItemDTO>> getProjectItemsList(@PathVariable int projectId) {
        List<ProjectItemDTO> items = projectService.getProjectItemsList(projectId);
        return ResponseEntity.ok(items);
    }

    // Mapping methods
    private ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getProjectDate(),
                project.getUser() != null ? project.getUser().getId() : null
        );
    }

    private Project toEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setId(dto.id());
        project.setTitle(dto.title());
        project.setProjectDate(dto.projectDate());
        // User must be set in service or elsewhere, as only userId is available in DTO
        return project;
    }
}