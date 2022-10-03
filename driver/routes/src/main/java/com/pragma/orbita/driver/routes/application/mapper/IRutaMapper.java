package com.pragma.orbita.driver.routes.application.mapper;

import com.pragma.orbita.driver.routes.application.dto.RutaDto;
import com.pragma.orbita.driver.routes.domain.model.Ruta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRutaMapper {
        RutaDto toDto(Ruta ruta);

        Ruta toDomain(RutaDto rutaDto);

        List<RutaDto> toDtoList(List<Ruta> rutaList);


}
