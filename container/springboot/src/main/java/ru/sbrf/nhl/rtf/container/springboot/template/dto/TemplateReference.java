package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TemplateReference implements Serializable {
    private Long id;
    private String name;
}
