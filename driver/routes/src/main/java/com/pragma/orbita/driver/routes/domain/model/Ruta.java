package com.pragma.orbita.driver.routes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
    private int idRuta;
    private String descripcion;
    private int idUsuario;
    private int cupos;
}
