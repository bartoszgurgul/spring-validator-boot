package pl.javastart.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.javastart.demo.model.Person;
import pl.javastart.demo.service.PersonService;

import java.sql.SQLOutput;

@SpringBootApplication
public class SpringValidatorBootApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringValidatorBootApplication.class, args);
        PersonService personService = context.getBean(PersonService.class);

        personService.addPerson(new Person("FirstName", "LastName", "test@test.com", 23));

        personService.addPerson(new Person("OneName", "TwoName", "złyMail.com", 0));

        System.out.println("Person " +personService.getPeople().size());
        personService.getPeople().forEach(System.out::println);

        context.close();

    }

    @Bean
    public Validator getValidator(){
        return new LocalValidatorFactoryBean();
    }

}
