package com.example.vulnerable.controller.assembler;

import com.example.vulnerable.dto.LineDto;
import com.example.vulnerable.models.Line;
import org.springframework.stereotype.Component;

@Component
public class LineAssembler implements Assembler<Line, LineDto>{

    @Override
    public Line dtoToModel(LineDto dto) {

        Line line = new Line();
        line.setLineFrom(dto.getLineFrom());
        line.setLineTo(dto.getLineTo());
        line.setMonthly(dto.getMonthly());
        line.setTwoWeekly(dto.getTwoWeekly());
        line.setThreeWeekly(dto.getThreeWeekly());
        line.setPeople(dto.getPeople());

        return line;
    }

    @Override
    public LineDto modelToDto(Line line) {

        LineDto lineDto = new LineDto();
        lineDto.setLineFrom(line.getLineFrom());
        lineDto.setLineTo(line.getLineTo());
        lineDto.setMonthly(line.getMonthly());
        lineDto.setTwoWeekly(line.getTwoWeekly());
        lineDto.setThreeWeekly(line.getThreeWeekly());
        lineDto.setPeople(line.getPeople());

        return lineDto;
    }

//    @Override
//    public Line update(LineDto dto) {
//
//        Optional<Line> lineEntity;
//        return null;
//    }
}
