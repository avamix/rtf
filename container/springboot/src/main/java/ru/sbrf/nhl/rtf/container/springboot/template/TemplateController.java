package ru.sbrf.nhl.rtf.container.springboot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.FilledTemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.KnownObject;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateData;

@RestController("/templates")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    @ResponseBody
    public KnownObject<TemplateData, String> getById(@PathVariable String templateId) {
        return templateService.getById(templateId);
    }

    @PostMapping("/create")
    @ResponseBody
    public KnownObject<TemplateData, String> create(@RequestBody TemplateData template) {
        return templateService.save(template);
    }

    @PostMapping("/update")
    @ResponseBody
    public KnownObject<TemplateData, String> update(@RequestBody KnownObject<TemplateData, String> template) {
        return templateService.update(template);
    }

    @PostMapping("/fill")
    @ResponseBody
    public void fill(@RequestBody FilledTemplateData template) {
        // todo: code another service + repository for entity FilledTempalte
        return;
    }
}
