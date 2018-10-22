package ru.sbrf.nhl.rtf.core.dao;

import org.springframework.stereotype.Repository;
import ru.sbrf.nhl.rtf.core.model.Project;

@Repository
public class ProjectDAO extends AbstractDAO<Project> {

    public ProjectDAO() {
        super(Project.class);
    }
}
