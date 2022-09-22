package com.pragma.orbita.driver.routes.infraestructure.persistence.mapper;

import com.pragma.orbita.driver.routes.domain.model.Barrio;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.BarrioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapperBarrioRepository {

    BarrioEntity toEntity(Barrio barrio);

    Barrio toDomain(BarrioEntity barrioEntity);
}
