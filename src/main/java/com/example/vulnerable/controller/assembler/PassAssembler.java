package com.example.vulnerable.controller.assembler;

import com.example.vulnerable.dto.PassDto;
import com.example.vulnerable.models.Pass;
import org.springframework.stereotype.Component;

@Component
public class PassAssembler implements Assembler<Pass, PassDto>{

    @Override
    public Pass dtoToModel(PassDto dto) {
        Pass pass = new Pass();
        pass.setSerialNumber(dto.getSerialNumber());
        pass.setStartDate(dto.getStartDate());
        pass.setEndDate(dto.getEndDate());
        pass.setPrice(dto.getPrice());
        pass.setPassTemplate(dto.getPassTemplate());
        pass.setLine(dto.getLine());
        //pass.setPerson(dto.getPerson());

        return pass;
    }

    @Override
    public PassDto modelToDto(Pass pass) {

        PassDto passDto = new PassDto();
        passDto.setSerialNumber(pass.getSerialNumber());
        passDto.setStartDate(pass.getStartDate());
        passDto.setEndDate(pass.getEndDate());
        passDto.setPrice(pass.getPrice());
        passDto.setPassTemplate(pass.getPassTemplate());
        passDto.setLine(pass.getLine());
        //passDto.setPerson(pass.getPerson());

        return passDto;
    }

//    @Override
//    public Pass update(PassDto dto) {
//        return null;
//    }
}
