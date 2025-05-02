package com.lucianvaleanu.controller;

import com.lucianvaleanu.model.Project;
import com.lucianvaleanu.service.ProjectService;
import com.lucianvaleanu.utils.dto.ProjectItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getAllProjectsByUserId(@PathVariable int userId) {
        List<Project> projects = projectService.getAllProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable int projectId, @RequestBody Project project) {
        if (!projectService.getAllProjectsByUserId(project.getUser().getId()).stream()
                .anyMatch(p -> p.getId().equals(projectId))) {
            return ResponseEntity.notFound().build();
        }
        project.setId(projectId);
        projectService.updateProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{projectId}/items")
    public ResponseEntity<List<ProjectItemDTO>> getProjectItemsList(@PathVariable int projectId) {
        List<ProjectItemDTO> items = projectService.getProjectItemsList(projectId);
        return ResponseEntity.ok(items);
    }
}