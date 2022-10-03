package com.pragma.orbita.driver.routes.infraestructure.persistence.mapper;

import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaBarrioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperRutaBarrioRepository {

    RutaBarrioEntity toEntity(RutaBarrio ruta);

    RutaBarrio toDomain(RutaBarrioEntity rutaBarrioEntity);

    List<RutaBarrio> toDomainList(List<RutaBarrioEntity> rutaBarrioEntities);

}
