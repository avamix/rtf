package ru.sbrf.nhl.rtf.container.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

//TODO - хз, что за конфликт транзакций хибернейта с JPA, пока по жесткому исключил HibernateJpaAutoConfiguration
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EntityScan(basePackages = {"ru.sbrf.nhl"})
@ComponentScan(basePackages = {"ru.sbrf.nhl"})
//TODO не айс, лучше черех xml тогда подключить
@ImportResource("classpath:dao.spring.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
