package com.pragma.orbita.driver.routes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RutaDto {
    private int idRuta;
    private String descripcion;
    @NotNull
    private int idUsuario;
    @NotNull
    @Min(1)
    @Max(4)
    private int cupos;
    private List<RutaBarrioDto> rutaBarrioList;
}
