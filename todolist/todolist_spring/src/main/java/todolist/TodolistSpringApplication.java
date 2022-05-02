package todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import todolist.stereotypes.ApplicationService;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@SpringBootApplication
@ComponentScan(
    includeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, classes = {ApplicationService.class}),
    }
)
public class TodolistSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistSpringApplication.class, args);
    }

}
