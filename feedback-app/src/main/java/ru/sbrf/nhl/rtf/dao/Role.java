package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Entity
@Data
@Builder
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    @Size(min = 1)
    @ManyToMany
    private Set<Ability> abilities;
}
