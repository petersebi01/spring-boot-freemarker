package com.example.vulnerable.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Szemely")
@XmlAccessorType(XmlAccessType.NONE)
public class Person extends Model{

    private String name;
    private Integer id;
    private String firm;
    private Line line;
    private ArrayList<Pass> passes;

    public Person(){
        passes = new ArrayList<>();
    }

    @XmlElement(name = "Nev")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @XmlElement(name = "Ceg")
    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    @XmlElement(name = "Berlet")
    public ArrayList<Pass> getPasses(){
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

    public void addPasses(Pass pass) {
        passes.add(pass);
    }
}
