package com.pragma.orbita.driver.routes.application.mapper;

import com.pragma.orbita.driver.routes.application.dto.RutaBarrioDto;
import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRutaBarrioMapper {

    RutaBarrio toDomain(RutaBarrioDto rutaBarrioDto);

    RutaBarrioDto toDto(RutaBarrio rutaBarrio);

    List<RutaBarrioDto> toDtoList(List<RutaBarrio> rutaBarrioList);
}
