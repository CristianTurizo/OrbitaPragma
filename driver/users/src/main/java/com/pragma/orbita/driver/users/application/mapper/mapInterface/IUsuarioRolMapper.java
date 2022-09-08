package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioRolMapper {

    UsuarioRolDtoRespuesta usuarioRolToRespuesta(UsuarioRol usuario);

    UsuarioRol consultaDtoToUsuarioRol(UsuarioRolDtoConsulta usuarioRolDtoConsulta);

    List<UsuarioRolDtoRespuesta> relacionesToRelacionesRespuesta(List<UsuarioRol> relaciones);

}
