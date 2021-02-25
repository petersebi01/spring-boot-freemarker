package com.example.vulnerable.repository;

import com.example.vulnerable.models.BusPassModel;
import com.example.vulnerable.models.Line;
import com.example.vulnerable.repository.jaxb.XmlMarshaller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LineRepository {

    public List<Line> findAllLines() {
        ArrayList<Line> line = new ArrayList<>(BusPassModel.getInstance().getLines());
        System.out.println(line);
        return line;
    }

    public Line findLinesByLinesFromAndLinesTo(String lineFrom, String lineTo){

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        Line line = null;

        for (Line value : lines) {
            if (value.getLineFrom().equals(lineFrom) && value.getLineTo().equals(lineTo))
                line = value;
        }

        return line;
    }

    public List<Line> findLinesByPerson(String name){

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        ArrayList<Line> line = new ArrayList<>();

        for (Line value : lines) {
            for (int j = 0; j < value.getPeople().size(); j++) {
                if (value.getPeople().get(j).getName().equals(name))
                    line.add(value);
            }
        }

        return line;
    }

    public Line createOrUpdateLine(String lineFrom, String lineTo, int monthly, int twoWeekly, int threeWeekly) {

        ArrayList<Line> lines = new ArrayList<>(BusPassModel.getInstance().getLines());
        Line line = null;
        for (Line line1 : lines) {
            if (line1.getLineFrom().equals(lineFrom) && line1.getLineTo().equals(lineTo)) {

                line1.setLineFrom(lineFrom);
                line1.setLineTo(lineTo);
                line1.setThreeWeekly(threeWeekly);
                line1.setMonthly(monthly);
                line1.setTwoWeekly(twoWeekly);


                line = line1;

                XmlMarshaller.save();
                break;

            }
        }

        if (line == null) {
            line = new Line();
            line.setLineFrom(lineFrom);
            line.setLineTo(lineTo);
            line.setMonthly(monthly);
            line.setThreeWeekly(threeWeekly);
            line.setTwoWeekly(twoWeekly);

            XmlMarshaller.save();
        }
        return line;
    }
}
