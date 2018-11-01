package ru.sbrf.nhl.rtf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.sbrf.nhl.rtf.dao.Ability;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.Feedback.FeedbackAuthor;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singleton;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * TODO: Remove
     */
    @Bean
    public CommandLineRunner testData(EntityManagerFactory emf) {
        return args -> {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            List<Ability> abilities = Stream.of("Java", "Gramatics", "Ansible", "Algorithms")
                    .map(name -> Ability.builder()
                            .name(name)
                            .build())
                    .peek(manager::persist)
                    .collect(Collectors.toList());

            List<Role> roles = Stream.of("Java dev", "Tester", "Analytic", "DevOps")
                    .map(name -> Role.builder()
                            .name(name)
                            .abilities(singleton(randomAbility(abilities)))
                            .build())
                    .peek(manager::persist)
                    .collect(Collectors.toList());

            List<Person> people = Stream.of("Alexander", "Evgeniya", "Konstantin", "Vladimir", "Denis")
                    .map(name -> {
                        Person person = Person.builder()
                                .fullName(name)
                                .grade(RANDOM.nextInt(15))
                                .successFactorId(name + "-" + RANDOM.nextInt())
                                .roles(singleton(roles.get(RANDOM.nextInt(roles.size()))))
                                .build();
                        if (0 == RANDOM.nextInt(10)) { // 10%
                            AbilitySnapshot snapshot = createAbilities(abilities, person);
                            person.setAbilities(new TreeSet<>(singleton(snapshot)));
                        }
                        return person;
                    })
                    .peek(manager::persist)
                    .collect(Collectors.toList());

            Stream.generate(() -> RANDOM.nextInt(100))
                    .limit(1000)
                    .map(value -> {
                        Person target = randomPerson(people);
                        Ability ability = randomAbility(abilities);
                        return Feedback.builder()
                                .ability(ability)
                                .author(createAuthor(people, ability))
                                .comment("Test comment on ability: " + ability.getName())
                                .postedAt(someDateInTheMonth())
                                .target(target)
                                .targetCurrentGrade(target.getGrade())
                                .value(value)
                                .build();
                    })
                    .forEach(manager::persist);
            manager.getTransaction().commit();
            manager.close();
        };
    }

    private AbilitySnapshot createAbilities(List<Ability> abilities, Person person) {
        return AbilitySnapshot.builder()
                .ability(randomAbility(abilities))
                .createdAt(someDateInTheMonth())
                .value(RANDOM.nextInt(100))
                .person(person)
                .build();
    }

    private Date someDateInTheMonth() {
        ZonedDateTime dateTime = ZonedDateTime.now()
                .minus(RANDOM.nextInt(30), ChronoUnit.DAYS)
                .minus(RANDOM.nextInt(13), ChronoUnit.HOURS)
                .minus(RANDOM.nextInt(60), ChronoUnit.MINUTES)
                .minus(RANDOM.nextInt(60), ChronoUnit.SECONDS);
        return Date.from(dateTime.toInstant());
    }

    private FeedbackAuthor createAuthor(List<Person> people, Ability ability) {
        Person person = randomPerson(people);
        FeedbackAuthor feedbackAuthor = FeedbackAuthor.builder()
                .valueOnAbility(findValueInAbility(person, ability))
                .person(person)
                .grade(person.getGrade())
                .build();
        if (0 == RANDOM.nextInt(10)) { // 10%
            feedbackAuthor.setPerson(null);
        }
        return feedbackAuthor;
    }

    private Integer findValueInAbility(Person person, Ability ability) {
        int defaultValue = 0;
        if (person.getAbilities() == null) {
            return defaultValue;
        }
        return person.getAbilities().stream()
                .filter(abilitySnapshot -> abilitySnapshot.getAbility().equals(ability))
                .map(AbilitySnapshot::getValue)
                .findFirst()
                .orElse(defaultValue);
    }

    private Person randomPerson(List<Person> people) {
        return people.get(RANDOM.nextInt(people.size()));
    }

    private Ability randomAbility(List<Ability> abilities) {
        return abilities.get(RANDOM.nextInt(abilities.size()));
    }
}
