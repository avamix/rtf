package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Builder
public class Role {
    @Id
    private String name;
    @NotNull
    @Size(min = 1)
    @ManyToMany
    private Set<Ability> abilities;
}
