package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import lombok.Builder;
import lombok.Data;
import ru.sbrf.nhl.rtf.container.springboot.template.dao.Template;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Builder
public class TemplateData {
    private String name;
    private List<FieldDto> fields;

    public static TemplateData from(Template savedTemplate) {
        return new TemplateData(savedTemplate.getName(), savedTemplate.getFields().stream()
                .map(FieldDto::from)
                .collect(toList())
        );
    }
}
