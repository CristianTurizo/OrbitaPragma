package com.pragma.orbita.driver.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    private int idRol;
    @NotBlank(message = "Debe tener un nombre valido")
    private String nombre;
    private String descripcion;
}
