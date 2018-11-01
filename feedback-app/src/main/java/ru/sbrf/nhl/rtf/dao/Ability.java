package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Ability {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
