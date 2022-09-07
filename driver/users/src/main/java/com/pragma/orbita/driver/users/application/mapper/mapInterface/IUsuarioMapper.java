package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    UsuarioDTORespuesta usuarioToDtoRespuesta(Usuario usuario);

    Usuario respuestaDtoToUsuario(UsuarioDTORespuesta usuarioDTORespuesta);

    UsuarioDtoConsulta usuarioToDtoConsulta(Usuario usuario);

    Usuario consultaDtoToUsuario(UsuarioDtoConsulta usuarioDTOConsulta);
}
