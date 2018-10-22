package ru.sbrf.nhl.rtf.container.springboot.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.UpdateRequest;
import ru.sbrf.nhl.rtf.container.springboot.templates.dto.ViewObject;

@RestController("/templates")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    @ResponseBody
    public ViewObject<TemplateData> getById(@PathVariable String templateId) {
        return templateService.getById(templateId);
    }

    @PostMapping("/create")
    @ResponseBody
    public ViewObject<TemplateData> create(@RequestBody TemplateData template) {
        return templateService.save(template);
    }

    @PostMapping("/update")
    @ResponseBody
    public ViewObject<TemplateData> update(@RequestBody UpdateRequest<TemplateData> template) {
        return templateService.update(template);
    }
}
