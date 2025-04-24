package com.lucianvaleanu.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "project_item")
public class ProjectItem {
    @SequenceGenerator(name = "project_item_id_gen", sequenceName = "project_id_seq", allocationSize = 1)
    @EmbeddedId
    private ProjectItemId id;

    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @MapsId("itemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "item_id", nullable = false)
    private ConstructionItem item;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public ProjectItemId getId() {
        return id;
    }

    public void setId(ProjectItemId id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ConstructionItem getItem() {
        return item;
    }

    public void setItem(ConstructionItem item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}