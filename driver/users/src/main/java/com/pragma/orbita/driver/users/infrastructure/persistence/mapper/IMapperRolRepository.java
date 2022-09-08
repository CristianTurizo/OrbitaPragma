package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapperRolRepository {

    RolEntity domainToEntity(Rol rol);

    Rol entityToDomain(RolEntity rolEntity);
}
