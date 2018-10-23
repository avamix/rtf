package ru.sbrf.nhl.rtf.container.springboot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.KnownObject;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateReference;
import ru.sbrf.nhl.rtf.core.model.Template;

@Transactional
@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public KnownObject<TemplateData, Long> save(TemplateData request) {
        Template template = templateRepository.save(new Template(/*...*/));
        return null; // todo:...
    }

    public KnownObject<TemplateData, Long> getById(Long templateId) {
        Template template = templateRepository.getOne(templateId);
        return null; // todo:...
    }

    public KnownObject<TemplateData, Long> update(KnownObject<TemplateData, Long> request) {
        Template template = templateRepository.getOne(request.getId());
        return null; // todo:...
    }

    public Page<TemplateReference> findAll(Pageable pageable) {
        return templateRepository.findAll(pageable).map(template -> TemplateReference.builder()
                .id(template.getId())
                .name(template.getName())
                .build());
    }
}
