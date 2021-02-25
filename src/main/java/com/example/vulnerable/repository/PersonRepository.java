package com.example.vulnerable.repository;

import com.example.vulnerable.models.BusPassModel;
import com.example.vulnerable.models.Line;
import com.example.vulnerable.models.Person;
import com.example.vulnerable.repository.jaxb.XmlMarshaller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PersonRepository {
    
    public Person findPersonByName(String name){

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        Person person = null;
        for(Line line : lines) {
            for (Person p : line.getPeople()) {
                if (p.getName().equals(name)) {
                    person = p;
                }
            }
        }
        return person;
    }

    public Person findPersonByNameByLine(String lineFrom, String lineTo, String name){

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        Person person = null;
        for(Line line : lines) {
            if(line.getLineFrom().equals(lineFrom) && line.getLineTo().equals(lineTo)) {
                for (Person p : line.getPeople()) {
                    if (p.getName().equals(name)) {
                        person = p;
                    }
                }
            }
        }
        return person;
    }

    public Person createPerson(String lineFrom, String lineTo, String name, int id, String firm) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());

        Person person = new Person();
        person.setName(name);
        person.setId(id);
        person.setFirm(firm);

        for (Line line : lines) {
            if (line.getLineFrom().equals(lineFrom) && line.getLineTo().equals(lineTo)) {
                person.setLine(line);
                line.addPerson(person);
            }
        }

        XmlMarshaller.save();
        return person;
    }

    public Person updatePerson(String name, int id, String firm) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());

        Person person = null;

        for (Line line1 : lines) {
            for (Person p : line1.getPeople()){
                if (p.getId() == id ){
                    p.setName(name);
                    p.setId(id);
                    p.setFirm(firm);
                    p.setLine(line1);

                    person = p;
                    XmlMarshaller.save();

                    break;
                }
            }
        }

        if (person == null) {

            //person = createPerson(lineFrom, lineTo, name, id, firm);
        }

        return person;
    }

    public void deletePerson(String name) {

    }
}
