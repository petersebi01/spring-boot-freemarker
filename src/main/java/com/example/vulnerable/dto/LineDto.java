package com.example.vulnerable.dto;

import com.example.vulnerable.models.Person;

import java.util.List;

public class LineDto extends AbstractModelDto {

    private String lineFrom;
    private String lineTo;
    private Integer monthly;
    private Integer twoWeekly;
    private Integer threeWeekly;
    private List<Person> people;

    public String getLineFrom() {
        return lineFrom;
    }

    public void setLineFrom(String lineFrom) {
        this.lineFrom = lineFrom;
    }

    public String getLineTo() {
        return lineTo;
    }

    public void setLineTo(String lineTo) {
        this.lineTo = lineTo;
    }

    public Integer getMonthly() {
        return monthly;
    }

    public void setMonthly(Integer monthly) {
        this.monthly = monthly;
    }

    public Integer getTwoWeekly() {
        return twoWeekly;
    }

    public void setTwoWeekly(Integer twoWeekly) {
        this.twoWeekly = twoWeekly;
    }

    public Integer getThreeWeekly() {
        return threeWeekly;
    }

    public void setThreeWeekly(Integer threeWeekly) {
        this.threeWeekly = threeWeekly;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
