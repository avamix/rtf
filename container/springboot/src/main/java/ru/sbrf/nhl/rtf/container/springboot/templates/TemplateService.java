package ru.sbrf.nhl.rtf.container.springboot.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.UpdateRequest;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.ViewObject;
import ru.sbrf.nhl.rtf.core.model.Template;

@Transactional
@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public ViewObject<TemplateData> save(TemplateData request) {
        Template template = templateRepository.save(new Template(/*...*/));
        return null; // todo:...
    }

    public ViewObject<TemplateData> getById(String templateId) {
        Template template = templateRepository.getOne(templateId);
        return null; // todo:...
    }

    public ViewObject<TemplateData> update(UpdateRequest<TemplateData> request) {
        Template template = templateRepository.getOne(request.getId());
        return null; // todo:...
    }
}
