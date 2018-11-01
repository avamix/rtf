package ru.sbrf.nhl.rtf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @NotNull
    private Person target;
    @NotNull
    private Integer targetCurrentGrade;
    @NotNull
    private Integer value;
    @Size(max = 500)
    private String comment;
    @OneToOne
    @NotNull
    private Ability ability;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date postedAt;

    @Embedded
    @NotNull
    @Valid
    private FeedbackAuthor author;

    @Embeddable
    @Data
    @Builder
    public static class FeedbackAuthor {
        /**
         * Ссылка на человека который дал оценку -
         * может быть не заполнен если оценка анонимная
         */
        @OneToOne
        private Person person;
        /**
         * Анонимный грейд автора оценки
         */
        @NotNull
        private Integer grade;
        /**
         * вес оценки по текущей характеристике
         */
        @NotNull
        private Integer weight;
    }
}
