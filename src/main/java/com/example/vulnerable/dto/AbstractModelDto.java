package com.example.vulnerable.dto;

import com.example.vulnerable.models.Line;

import java.util.ArrayList;

public abstract class AbstractModelDto {

    private ArrayList<Line> line = new ArrayList<>();

    public ArrayList<Line> getLines() {
        return line;
    }

    public void setLines(ArrayList<Line> line) {
        this.line = line;
    }
}
