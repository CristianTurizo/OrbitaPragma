package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRolMapper {

    RolDtoRespuesta rolToDtoRespuesta(Rol rol);

    Rol respuestaDtoToRol (RolDtoRespuesta rolDTORespuesta);

    RolDtoConsulta rolToDtoConsulta (Rol rol);

    Rol consultaDtoToRol (RolDtoConsulta rolDTOConsulta);
}
