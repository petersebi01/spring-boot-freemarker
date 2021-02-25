package com.example.vulnerable.dto;

import com.example.vulnerable.models.Line;

public class PassDto extends AbstractModelDto {

    private String startDate;
    private String endDate;
    private Line line;
    private String serialNumber;
    private int price;
    private int passTemplate;
    //private Person person;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPassTemplate() {
        return passTemplate;
    }

    public void setPassTemplate(int passTemplate) {
        this.passTemplate = passTemplate;
    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
}
