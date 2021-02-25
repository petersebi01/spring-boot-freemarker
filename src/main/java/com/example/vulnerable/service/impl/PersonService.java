package com.example.vulnerable.service.impl;

import com.example.vulnerable.models.Person;
import com.example.vulnerable.repository.PersonRepository;
import com.example.vulnerable.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    private Logger logger;

    @Autowired
    public PersonService(final PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person login(String lineFrom, String lineTo, Person person){
        try {

            Person findPerson = personRepository.findPersonByNameByLine(lineFrom, lineTo, person.getName());
            if(findPerson == null){
                person = createPerson(lineFrom, lineTo, person);
            }
            return person;
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find person.", e);
            throw new ServiceException("Something went wrong while finding person", e);
        }
    }

    public Person findPersonByName(String name){
        try {
            return personRepository.findPersonByName(name);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find person.", e);
            throw new ServiceException("Something went wrong while finding person", e);
        }
    }

    public Person findPersonByNameByLine(String lineFrom, String lineTo, String name) {
        try {
            return personRepository.findPersonByNameByLine(lineFrom, lineTo, name);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find person.", e);
            throw new ServiceException("Something went wrong while finding person", e);
        }
    }

    public Person createPerson(String lineFrom, String lineTo, Person person) {
        try {
            return personRepository.createPerson(lineFrom, lineTo, person.getName(), person.getId(), person.getFirm());
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find Users.", e);
            throw new ServiceException("Something went wrong while finding users", e);
        }
    }

    public Person createOrUpdatePerson(Person person) {
        try {
            Person updatePerson = personRepository.updatePerson(person.getName(), person.getId(), person.getFirm());
            return updatePerson;
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find Users.", e);
            throw new ServiceException("Something went wrong while finding users", e);
        }
    }

    public void deletePerson(String name) {
        try {
            personRepository.deletePerson(name);
        } catch (Exception e) {
            logger.error("Something went wrong. Cannot find person.", e);
            throw new ServiceException("Something went wrong while finding person", e);
        }
    }
}
