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
public class RutaBarrio {
    private int idRutaBarrio;
    private int idRuta;
    private int idBarrio;
    private int indice;
    private String puntoEncuentro;
}
