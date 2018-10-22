package ru.sbrf.nhl.rtf.api.restapi;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sbrf.nhl.rtf.core.model.Template;

import java.util.List;

public interface TemplateController {

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    void createTemplate(@RequestBody Template template);

    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    List<String> getTemplates();
}
