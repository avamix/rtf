package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Data
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String successFactorId;
    private String fullName;
    @ManyToMany
    private Set<Role> roles;
    @NotNull
    private Long grade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @OrderBy("createdBy DESC")
    private SortedSet<AbilitySnapshot> abilities;
}
