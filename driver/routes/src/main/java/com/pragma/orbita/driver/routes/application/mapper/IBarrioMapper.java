package com.pragma.orbita.driver.routes.application.mapper;

import com.pragma.orbita.driver.routes.application.dto.BarrioDto;
import com.pragma.orbita.driver.routes.domain.model.Barrio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBarrioMapper {

    BarrioDto toDto(Barrio barrio);

    Barrio toDomain(BarrioDto barrioDto);
}
