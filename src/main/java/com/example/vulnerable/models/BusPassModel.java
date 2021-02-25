package com.example.vulnerable.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Berletek")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BusPassModel {

    private ArrayList<Line> passes = new ArrayList<>();

    private static BusPassModel INSTANCE = new BusPassModel();

    public static BusPassModel getInstance() {
        return INSTANCE;
    }

    public static void setInstance(BusPassModel busPassModel) {
        INSTANCE = busPassModel;
    }

    @XmlElement(name = "Jarat")
    public ArrayList<Line> getLines() {
        return passes;
    }

    public void setLines(ArrayList<Line> passes) {
        this.passes = passes;
    }
}
