package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateData {
    private String name;
    private List<FieldDefinition> fields;
}
