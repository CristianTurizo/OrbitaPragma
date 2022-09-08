package com.pragma.orbita.driver.users.application.DTOConsulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDtoConsulta {
    private int idRol;
    private String nombre;
    private String descripcion;
}
