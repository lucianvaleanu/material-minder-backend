package com.lucianvaleanu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ProjectItemId implements java.io.Serializable {
    private static final long serialVersionUID = 721266166875641541L;
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectItemId entity = (ProjectItemId) o;
        return Objects.equals(this.itemId, entity.itemId) &&
                Objects.equals(this.projectId, entity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, projectId);
    }

}