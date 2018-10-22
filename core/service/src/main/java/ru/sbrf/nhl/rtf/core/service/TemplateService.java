package ru.sbrf.nhl.rtf.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.core.dao.TemplateDAO;
import ru.sbrf.nhl.rtf.core.model.Template;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateDAO templateDAO;

    @Transactional
    public void createTemplate(Template template) {
        templateDAO.save(template);
    }

    @Transactional(readOnly = true)
    public List<Template> getAll(){
        return templateDAO.getAll();
    }

}
