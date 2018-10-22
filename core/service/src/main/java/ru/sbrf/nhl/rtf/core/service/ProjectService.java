package ru.sbrf.nhl.rtf.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.core.dao.ProjectDAO;
import ru.sbrf.nhl.rtf.core.model.Project;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Transactional
    public void createProject(Project project){
        projectDAO.save(project);
    }

    @Transactional(readOnly = true)
    public List<Project> getAll(){
        return projectDAO.getAll();
    }
}
