package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUsuarioRolRepository {

    UsuarioRolEntity usuarioRolToEntity(UsuarioRol usuarioRol);

    List<UsuarioRol> entitiesToDomain(List<UsuarioRolEntity> entities);

    UsuarioRol entityToDomain(UsuarioRolEntity usuarioRolEntity);


}
