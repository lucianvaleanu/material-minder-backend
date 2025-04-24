package com.lucianvaleanu.repository;

import com.lucianvaleanu.model.ProjectItem;
import com.lucianvaleanu.utils.dto.ProjectItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectItemRepository extends JpaRepository<ProjectItem, Integer> {

    @Query("SELECT new com.lucianvaleanu.utils.dto.ProjectItemDTO(c.name, pi.quantity) " +
            "FROM ProjectItem pi " +
            "JOIN pi.item c " +
            "WHERE pi.project.id = :projectId")
    List<ProjectItemDTO> findItemsByProjectId(@Param("projectId") Integer projectId);
}