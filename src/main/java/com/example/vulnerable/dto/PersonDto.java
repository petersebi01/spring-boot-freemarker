package com.example.vulnerable.dto;

import com.example.vulnerable.models.Line;
import com.example.vulnerable.models.Pass;

import java.util.ArrayList;

public class PersonDto extends AbstractModelDto {

    private String name;
    private Integer id;
    private String firm;
    private Line line;
    private ArrayList<Pass> passes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public ArrayList<Pass> getPasses() {
        return passes;
    }

    public void setPasses(ArrayList<Pass> passes) {
        this.passes = passes;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
