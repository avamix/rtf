package ru.sbrf.nhl.rtf.container.springboot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.container.springboot.template.dao.Field;
import ru.sbrf.nhl.rtf.container.springboot.template.dao.Template;
import ru.sbrf.nhl.rtf.container.springboot.template.dao.TemplateRepository;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.KnownObject;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateReference;

import java.util.stream.Collectors;

@Transactional
@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public KnownObject<TemplateData, Long> save(TemplateData request) {
        Template template = templateRepository.save(Template.builder()
                .fields(request.getFields().stream()
                        .map(f -> Field.builder()
                                .name(f.getName())
                                .label(f.getLabel())
                                .build())
                        .collect(Collectors.toList()))
                .name(request.getName())
                .build()
        );
        Template savedTemplate = templateRepository.save(template);
        return new KnownObject<>(template.getId(), template.getVersion(), TemplateData.from(savedTemplate));
    }

    public KnownObject<TemplateData, Long> getById(Long templateId) {
        Template template = templateRepository.getOne(templateId);
        return new KnownObject<>(template.getId(), template.getVersion(), TemplateData.from(template));
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
