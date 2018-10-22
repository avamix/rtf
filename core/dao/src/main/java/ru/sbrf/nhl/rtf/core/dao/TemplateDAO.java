package ru.sbrf.nhl.rtf.core.dao;

import org.springframework.stereotype.Repository;
import ru.sbrf.nhl.rtf.core.model.Template;

@Repository
public class TemplateDAO extends AbstractDAO<Template> {

    public TemplateDAO() {
        super(Template.class);
    }
}
