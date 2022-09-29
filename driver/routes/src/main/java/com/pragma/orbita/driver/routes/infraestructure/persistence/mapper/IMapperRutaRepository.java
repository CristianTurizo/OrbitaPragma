package com.pragma.orbita.driver.routes.infraestructure.persistence.mapper;

import com.pragma.orbita.driver.routes.domain.model.Ruta;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperRutaRepository {
    RutaEntity toEntity(Ruta ruta);

    Ruta toDomain(RutaEntity rutaEntity);

    List<Ruta> toDomainList(List<RutaEntity> rutaEntities);
}
