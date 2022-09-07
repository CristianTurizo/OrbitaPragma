package com.pragma.orbita.driver.users.application.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRolDtoRespuesta {
    private UsuarioDTORespuesta usuario;
    private List<RolDtoRespuesta> roles;
}
