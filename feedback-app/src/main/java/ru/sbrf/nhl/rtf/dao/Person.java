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

/**
 * Элемент справчника всех людей участвующих в системе Feedback
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Уникальный идентификатор пользователя в сервисе аутентификации
     */
    private String successFactorId;
    /**
     * ФИО пользователя
     */
    private String fullName;
    /**
     * Список всех зон ответсвенностей у данного пользователя
     */
    @ManyToMany
    private Set<Role> roles;
    /**
     * Грейд клиента
     */
    @NotNull
    private Integer grade;
    /**
     * Множество всех срезов компетенций данного сотркдника отсортированных по дате среза
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @OrderBy("createdAt DESC")
    private SortedSet<AbilitySnapshot> abilities;
}
