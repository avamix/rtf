package ru.sbrf.nhl.rtf.container.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.sbrf.nhl.rtf.container.springboot.template.TemplateService;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.RangeFieldDto;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.SelectFieldDto;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TemplateData;
import ru.sbrf.nhl.rtf.container.springboot.template.dto.TextFieldDto;

import java.util.Arrays;

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
    public CommandLineRunner testData(TemplateService templateService) {
        return args -> {
            TemplateData data = new TemplateData("Template 1", Arrays.asList(
                    new TextFieldDto("name", "Enter your name"),
                    new RangeFieldDto("age", "Set your age", 18, 45),
                    new SelectFieldDto("profession", "Select your profession", Arrays.asList(
                            new SelectFieldDto.Option("programmer", "Programmer"),
                            new SelectFieldDto.Option("teacher", "Teacher"),
                            new SelectFieldDto.Option("psychologist", "Psychologist"),
                            new SelectFieldDto.Option("dummy", "Have no profession")
                    ))
            ));
            templateService.save(data);
            data = new TemplateData("Template 2", Arrays.asList(
                    new TextFieldDto("name", "Enter your name"),
                    new RangeFieldDto("stars", "Enter your satisfaction", 0, 5),
                    new SelectFieldDto("opinion", "Do you understand the teacher?", Arrays.asList(
                            new SelectFieldDto.Option("yes", "Yes"),
                            new SelectFieldDto.Option("no", "No")
                    ))
            ));
            templateService.save(data);
        };
    }
}
