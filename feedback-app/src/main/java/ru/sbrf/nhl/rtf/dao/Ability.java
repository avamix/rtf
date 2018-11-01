package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ability {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3)
    private String name;
}
