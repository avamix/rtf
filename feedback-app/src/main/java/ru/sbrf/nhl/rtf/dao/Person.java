package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Set<Person> heads;
    private String successFactorId;
    private String fullName;
    private Integer currentWeight;

}
