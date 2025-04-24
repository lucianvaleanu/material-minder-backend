package com.lucianvaleanu.model;

import java.time.LocalDate;
import java.util.Objects;

public class Project {
    private Integer id;
    private String title;
    private LocalDate date;

    public Project(Integer id, String title, LocalDate date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(title, project.title) && Objects.equals(date, project.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date);
    }
}
