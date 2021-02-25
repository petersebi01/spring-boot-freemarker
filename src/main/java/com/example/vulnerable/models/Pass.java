package com.example.vulnerable.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Berlet")
@XmlAccessorType(XmlAccessType.NONE)
public class Pass extends Model{

    private String startDate;
    private String endDate;
    private Line line;
    private String serialNumber;
    private int price;
    private int passTemplate;
    private Person person;

    @XmlElement(name = "Sorszam")
    public String getSerialNumber(){
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @XmlElement(name = "Mettol")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @XmlElement(name = "Meddig")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @XmlElement(name = "Osszeg")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    @XmlElement(name = "sablon")
    public int getPassTemplate() {
        return passTemplate;
    }

    public void setPassTemplate(int passTemplate) {
        this.passTemplate = passTemplate;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
