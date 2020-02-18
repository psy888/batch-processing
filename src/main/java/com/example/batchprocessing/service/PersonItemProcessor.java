package com.example.batchprocessing.service;

import com.example.batchprocessing.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(final Person person) throws Exception {
        log.info("Transform : " + person);
        return new Person(
                person.getFirstName().toUpperCase(),
                person.getLastName().toUpperCase()
        );
    }
}
