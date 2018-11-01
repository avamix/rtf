package ru.sbrf.nhl.rtf.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @NotNull
    private Person target;
    @NotNull
    private Long targetCurrentGrade;
    @NotNull
    private Long value;
    private String comment;
    @OneToOne
    @NotNull
    private Ability ability;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date postedAt;
    /**
     * Ссылка на человека который дал оценку -
     * может быть не заполнен если оценка анонимная
     */
    @OneToOne
    private Person postedBy;
    /**
     * Анонимный грейд автора оценки
     */
    @NotNull
    private Long postedByGrade;
    /**
     * Анонимный вес автора оценки по текущей характеристике
     */
    @NotNull
    private Long postedByWeightOnAbility;
}
