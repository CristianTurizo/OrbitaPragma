package com.pragma.orbita.driver.users.application.DTOConsulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolDtoConsulta {
    private int idUsuarioRol;
    private int idUsuario;
    private int idRol;
}
