package pl.javastart.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.javastart.demo.model.Person;

@Service
public class PersonService {

    private Set<Person> people;

    private Validator validator;

    @Autowired
    public PersonService(Validator validator) {
        this.validator = validator;
        this.people = new HashSet<>();
    }

    public void addPerson(Person person) {
        Errors errors = new BeanPropertyBindingResult(person, "person");
        validator.validate(person, errors);
        if (errors.hasErrors()){
            System.out.println("Spring Validator znalazł: "+errors.getErrorCount() +" błędów");
            errors.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
        }else {
            people.add(person);
        }


    }

    public Set<Person> getPeople() {
        return this.people;
    }
}