package ru.sbrf.nhl.rtf.container.springboot.template.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
public class Template implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    private String name;
    @OneToMany(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "template_id")
    private List<Field> fields;

    public Template() {
    }

    public Template(String name, List<Field> fields) {
        this.name = name;
        this.fields = fields;
    }

    public Template(Long id, Long version, String name, List<Field> fields) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.fields = fields;
    }
}
