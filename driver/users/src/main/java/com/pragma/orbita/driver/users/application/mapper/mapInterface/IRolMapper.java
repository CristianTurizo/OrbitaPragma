package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRolMapper {

    RolDtoRespuesta rolToDtoRespuesta(Rol rol);

    Rol respuestaDtoToRol (RolDtoRespuesta rolDTORespuesta);

    RolDTOConsulta rolToDtoConsulta (Rol rol);

    Rol consultaDtoToRol (RolDTOConsulta rolDTOConsulta);
}
