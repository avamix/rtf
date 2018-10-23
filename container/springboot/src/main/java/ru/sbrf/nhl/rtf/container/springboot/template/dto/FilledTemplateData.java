package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilledTemplateData implements Serializable {
    private Long templateId;
    private List<FieldValue> fields;
}
