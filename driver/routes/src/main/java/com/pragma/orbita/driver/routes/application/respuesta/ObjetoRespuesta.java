package com.pragma.orbita.driver.routes.application.respuesta;

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
public class ObjetoRespuesta<T> {
    private T dato;
    private String mensaje;
}
