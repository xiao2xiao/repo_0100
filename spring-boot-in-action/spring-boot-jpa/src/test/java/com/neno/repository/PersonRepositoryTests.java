package com.neno.repository;

import com.neno.JpaApplicationTests;
import com.neno.model.Name;
import com.neno.model.Person;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/3/26 23:43
 */
public class PersonRepositoryTests extends JpaApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTests.class);

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave() {
        Name name = new Name("aaa", "bbb");
        Person person = new Person(name, 10);
        personRepository.save(person);
    }

    @Test
    public void testFindOne() {
        Name name = new Name("hao", "are");
        Person person = personRepository.findById(name).get();
        LOGGER.info(person.toString());
    }

    @Test
    public void testFindAll(){
        List<Person> people = personRepository.findAll();
        LOGGER.info("总人数："+people.size());
        people.forEach(person -> LOGGER.info(person.toString()));
    }

}
