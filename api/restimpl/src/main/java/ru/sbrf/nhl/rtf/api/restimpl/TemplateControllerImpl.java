package ru.sbrf.nhl.rtf.api.restimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.api.restapi.TemplateController;
import ru.sbrf.nhl.rtf.core.model.Template;
import ru.sbrf.nhl.rtf.core.service.TemplateService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TemplateControllerImpl implements TemplateController {

    @Autowired
    private TemplateService templateService;

    @Override
    public void createTemplate(Template template) {
        templateService.createTemplate(template);
    }

    @Override
    public List<String> getTemplates() {
        return templateService.getAll().stream().map(Template::getName).collect(Collectors.toList());
    }
}
