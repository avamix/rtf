package ru.sbrf.nhl.rtf.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Ability {
    @Id
    private String name;
}
