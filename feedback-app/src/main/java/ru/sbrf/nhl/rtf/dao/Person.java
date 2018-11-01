package ru.sbrf.nhl.rtf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String successFactorId;
    private String fullName;
    @ManyToMany
    private Set<Role> roles;
    @NotNull
    private Integer grade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @OrderBy("createdAt DESC")
    private SortedSet<AbilitySnapshot> abilities;
}
