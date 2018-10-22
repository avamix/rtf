package ru.sbrf.nhl.core.model;

import ru.sbrf.nhl.core.model.component.ProjectStatus;

import java.time.LocalDate;

public class Project extends AbstractEntity {

    private String name;

    private String description;

    private ProjectStatus status;

    private LocalDate createDate;

    private Template template;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
