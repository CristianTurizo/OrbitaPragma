package com.pragma.orbita.driver.routes.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RutaBarrioDto {
    private int idRutaBarrio;
    private int idRuta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idBarrio;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreBarrio;
    private int indice;
    private String puntoEncuentro;
}
