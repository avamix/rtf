package ru.sbrf.nhl.rtf.container.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EntityScan(basePackages = {"ru.sbrf.nhl"})
@ComponentScan(basePackages = {"ru.sbrf.nhl"})
//TODO не айс, лучше черех xml тогда подключить
@ImportResource("classpath:dao.spring.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
