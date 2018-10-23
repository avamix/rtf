package ru.sbrf.nhl.rtf.container.springboot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.FilledTemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.KnownObject;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateReference;

@RestController("/templates")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    @ResponseBody
    public KnownObject<TemplateData, Long> getById(@PathVariable Long templateId) {
        return templateService.getById(templateId);
    }

    @GetMapping
    @ResponseBody
    public Page<TemplateReference> findAll(Pageable pageable) {
        return templateService.findAll(pageable);
    }

    @PostMapping("/create")
    @ResponseBody
    public KnownObject<TemplateData, Long> create(@RequestBody TemplateData template) {
        return templateService.save(template);
    }

    @PostMapping("/update")
    @ResponseBody
    public KnownObject<TemplateData, Long> update(@RequestBody KnownObject<TemplateData, Long> template) {
        return templateService.update(template);
    }

    @PostMapping("/fill")
    @ResponseBody
    public void fill(@RequestBody FilledTemplateData template) {
        // todo: code another service + repository for entity FilledTempalte
        return;
    }
}
