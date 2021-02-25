package com.example.vulnerable.controller.assembler;

import com.example.vulnerable.dto.AbstractModelDto;
import com.example.vulnerable.models.Model;

public interface Assembler <MODEL extends Model, DTO extends AbstractModelDto> {

    MODEL dtoToModel(DTO dto);
    DTO modelToDto(MODEL model);
    //MODEL update(DTO dto);
}
