package ru.sbrf.nhl.rtf.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Конфигурационный класс веб-приложения
 */
@EnableWebMvc
@SpringBootApplication
public class WebAppConfig extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(WebAppConfig.class, args);
    }

    /**
     * Определяем папку с ресурсами
     *
     * @param registry instance of resource handler
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**")
                .addResourceLocations("/WEB-INF/pages/");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
