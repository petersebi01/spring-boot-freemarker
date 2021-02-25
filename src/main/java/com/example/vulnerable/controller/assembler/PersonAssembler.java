package com.example.vulnerable.controller.assembler;

import com.example.vulnerable.dto.PersonDto;
import com.example.vulnerable.models.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonAssembler implements Assembler<Person, PersonDto>{

    @Override
    public Person dtoToModel(PersonDto dto) {

        Person person = new Person();

        if (dto != null) {
            person.setName(dto.getName());
            person.setId(dto.getId());
            person.setFirm(dto.getFirm());
            person.setPasses(dto.getPasses());
        }
        return person;
    }

    @Override
    public PersonDto modelToDto(Person model) {

        PersonDto personDto = new PersonDto();
        if (model != null) {
            personDto.setName(model.getName());
            personDto.setId(model.getId());
            personDto.setFirm(model.getFirm());
            personDto.setPasses(model.getPasses());
        }

        return personDto;
    }
}
