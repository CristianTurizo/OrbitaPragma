package com.pragma.orbita.driver.routes.infraestructure.controller;

import com.pragma.orbita.driver.routes.application.dto.BarrioDto;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.routes.application.service.BarrioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("barrio")
@RequiredArgsConstructor
public class BarrioController {

    private final BarrioService barrioService;

    @PostMapping
    public ResponseEntity<ObjetoRespuesta<BarrioDto>> guardarBarrio(@NotNull @RequestBody BarrioDto barrioDto) {
        ObjetoRespuesta<BarrioDto> barrioNuevo = barrioService.guardarBarrio(barrioDto);

        return barrioNuevo.getDato() == null
                ? new ResponseEntity<>(barrioNuevo, HttpStatus.CONFLICT)
                : new ResponseEntity<>(barrioNuevo, HttpStatus.CREATED);
    }

    @GetMapping("/{nombreBarrio}")
    public ResponseEntity<ObjetoRespuesta<BarrioDto>> buscarBarrioPorNombre(@NotNull @PathVariable String nombreBarrio) {
        ObjetoRespuesta<BarrioDto> barrio = barrioService.buscarBarrioPorNombre(nombreBarrio);

        return barrio.getDato() == null
                ? new ResponseEntity<>(barrio, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(barrio, HttpStatus.OK);
    }
}
