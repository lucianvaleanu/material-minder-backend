package com.lucianvaleanu.repository;

import com.lucianvaleanu.model.Project;
import com.lucianvaleanu.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        userRepository.save(testUser);

        Project project = new Project();
        project.setTitle("Test Project");
        project.setProjectDate(LocalDate.now());
        project.setUser(testUser);
        projectRepository.save(project);
    }

    @Test
    void testFindByUserId() {
        List<Project> projects = projectRepository.findByUserId(testUser.getId());
        assertFalse(projects.isEmpty());
        assertEquals(1, projects.size());
        assertEquals("Test Project", projects.get(0).getTitle());
    }

    @Test
    void testFindById() {
        Project project = projectRepository.findByUserId(testUser.getId()).get(0);
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertTrue(foundProject.isPresent());
        assertEquals(project.getId(), foundProject.get().getId());
    }

    @Test
    void testSave() {
        Project newProject = new Project();
        newProject.setTitle("New Project");
        newProject.setProjectDate(LocalDate.now());
        newProject.setUser(testUser);

        Project savedProject = projectRepository.save(newProject);
        assertNotNull(savedProject.getId());
        assertEquals("New Project", savedProject.getTitle());
    }

    @Test
    void testDeleteById() {
        Project project = projectRepository.findByUserId(testUser.getId()).get(0);
        projectRepository.deleteById(project.getId());

        Optional<Project> deletedProject = projectRepository.findById(project.getId());
        assertFalse(deletedProject.isPresent());
    }

    @Test
    void testUpdate() {
        Project project = projectRepository.findByUserId(testUser.getId()).get(0);
        project.setTitle("Updated Project");
        projectRepository.update(project);

        Optional<Project> updatedProject = projectRepository.findById(project.getId());
        assertTrue(updatedProject.isPresent());
        assertEquals("Updated Project", updatedProject.get().getTitle());
    }
}