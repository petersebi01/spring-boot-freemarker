package com.example.vulnerable.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Jarat")
@XmlAccessorType(XmlAccessType.NONE)
public class Line extends Model{

    private String lineFrom;
    private String lineTo;
    private Integer monthly;
    private Integer twoWeekly;
    private Integer threeWeekly;
    private List<Person> people;

    public Line(){

        people = new ArrayList<>();
    }

    @XmlElement(name = "Honnan")
    public String getLineFrom() {
        return lineFrom;
    }

    public void setLineFrom(String lineFrom) {
        this.lineFrom = lineFrom;

    }

    @XmlElement(name = "Hova")
    public String getLineTo() {
        return lineTo;
    }

    public void setLineTo(String lineTo) {
        this.lineTo = lineTo;
    }

    public int getPrice(String text) {

        int price;

        switch (text) {
            case "30 nap":
                price = monthly;
                break;
            case "14 nap":
                price = twoWeekly;
                break;
            case "21 nap":
                price = threeWeekly;
                break;
            default:
                price = 0;
        }

        return price;
    }

    @XmlElement(name = "Honapos")
    public int getMonthly() {
        return monthly;
    }

    @XmlElement(name = "Kethetes")
    public int getTwoWeekly() {
        return twoWeekly;
    }

    @XmlElement(name = "Haromhetes")
    public int getThreeWeekly() {
        return threeWeekly;
    }

    public void setMonthly(int honapos) {

        this.monthly = honapos;
    }

    public void setTwoWeekly(int twoWeekly) {

        this.twoWeekly = twoWeekly;
    }

    public void setThreeWeekly(int threeWeekly) {

        this.threeWeekly = threeWeekly;
    }

    @XmlElement(name = "Szemely")
    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person){
        people.add(person);
    }
}