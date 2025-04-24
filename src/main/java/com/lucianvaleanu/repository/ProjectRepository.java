package com.lucianvaleanu.repository;

import com.lucianvaleanu.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();
    Optional<Project> findById(int id);
    Project save(Project project);
    void deleteById(int id);

    @Modifying
    @Query("UPDATE Project p SET p.title = :#{#project.title}, p.projectDate = :#{#project.projectDate} WHERE p.id = :#{#project.id}")
    void update(@Param("project") Project project);
}
