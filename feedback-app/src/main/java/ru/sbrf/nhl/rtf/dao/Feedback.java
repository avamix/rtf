package ru.sbrf.nhl.rtf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbrf.nhl.rtf.rest.dto.Source;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Отзыв по компетенции
 */
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
    /**
     * Грейд оцениваемого сотрудника в момент оценки
     */
    @NotNull
    private Integer targetCurrentGrade;
    /**
     * Оценка от 0 - 100
     */
    @NotNull
    private Integer value;
    @Size(max = 500)
    private String comment;
    /**
     * Оцениваемая компетенция
     */
    @OneToOne
    @NotNull
    private Ability ability;
    /**
     * Время предоставления оценки
     */
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date postedAt;

    /**
     * Информация о оценивателе
     */
    @Embedded
    @NotNull
    @Valid
    private FeedbackAuthor author;

    /**
     * Источник получения оценки
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    // todo: replace with Entity
    private Source source = Source.FORM;

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
         * Грейд автора отзыва в момент проставления оценки
         */
        private int grade;
        /**
         * Оценка по текущей характеристике от 0 - 100
         */
        @Max(100)
        @Min(0)
        private int valueOnAbility;
    }
}
