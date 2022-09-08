package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDtoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    UsuarioDtoRespuesta usuarioToDtoRespuesta(Usuario usuario);

    Usuario respuestaDtoToUsuario(UsuarioDtoRespuesta usuarioDTORespuesta);

    UsuarioDtoConsulta usuarioToDtoConsulta(Usuario usuario);

    Usuario consultaDtoToUsuario(UsuarioDtoConsulta usuarioDTOConsulta);
}
