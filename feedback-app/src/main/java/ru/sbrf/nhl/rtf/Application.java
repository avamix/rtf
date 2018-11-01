package ru.sbrf.nhl.rtf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.sbrf.nhl.rtf.dao.Ability;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singleton;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

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
            Random random = new Random();
            List<Ability> abilities = Stream.of("Java", "Gramatics", "Ansible", "Algorithms")
                    .map(name -> Ability.builder()
                            .name(name)
                            .build())
                    .peek(manager::persist)
                    .collect(Collectors.toList());

            List<Role> roles = Stream.of("Java dev", "Tester", "Analytic", "DevOps")
                    .map(name -> Role.builder()
                            .name(name)
                            .abilities(singleton(abilities.get(random.nextInt(abilities.size()))))
                            .build())
                    .peek(manager::persist)
                    .collect(Collectors.toList());

            List<Person> people = Stream.of("Alexander", "Evgeniya", "Konstantin", "Vladimir", "Denis")
                    .map(name -> Person.builder()
                            .fullName(name)
                            .grade(random.nextInt(15))
                            .successFactorId(name + "-" + random.nextInt())
                            .roles(singleton(roles.get(random.nextInt(roles.size()))))
                            .build())
                    .peek(manager::persist)
                    .collect(Collectors.toList());
            manager.getTransaction().commit();
            manager.close();
        };
    }
}
