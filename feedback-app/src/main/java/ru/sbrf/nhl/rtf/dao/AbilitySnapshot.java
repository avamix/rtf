package ru.sbrf.nhl.rtf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Builder
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilitySnapshot implements Comparable<AbilitySnapshot> {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createdAt;
    @NotNull
    @OneToOne
    private Ability ability;
    @NotNull
    private Integer value;
    @NotNull
    @ManyToOne
    private Person person;

    @Override
    public int compareTo(AbilitySnapshot o) {
        return createdAt.compareTo(o.createdAt);
    }
}
