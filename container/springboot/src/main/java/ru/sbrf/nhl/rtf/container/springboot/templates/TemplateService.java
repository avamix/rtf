package ru.sbrf.nhl.rtf.container.springboot.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.KnownObject;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.TemplateData;
import ru.sbrf.nhl.rtf.core.model.Template;

@Transactional
@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public KnownObject<TemplateData, String> save(TemplateData request) {
        Template template = templateRepository.save(new Template(/*...*/));
        return null; // todo:...
    }

    public KnownObject<TemplateData, String> getById(String templateId) {
        Template template = templateRepository.getOne(templateId);
        return null; // todo:...
    }

    public KnownObject<TemplateData, String> update(KnownObject<TemplateData, String> request) {
        Template template = templateRepository.getOne(request.getId());
        return null; // todo:...
    }
}
