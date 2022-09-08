package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapperUsuarioRepository {

    UsuarioEntity domainToEntity(Usuario usuario);

    Usuario entityToDomain(UsuarioEntity usuarioEntity);
}
