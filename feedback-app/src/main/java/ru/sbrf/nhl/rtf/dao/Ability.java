package ru.sbrf.nhl.rtf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Элекмент справочника квалификаций
 */
@Entity
@Builder
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3)
    private String name;
}
