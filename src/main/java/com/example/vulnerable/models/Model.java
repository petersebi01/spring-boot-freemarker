package com.example.vulnerable.models;

import java.util.ArrayList;

public abstract class Model {

    private ArrayList<Line> lines = new ArrayList<>();

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
}
