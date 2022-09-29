package com.pragma.orbita.driver.routes.infraestructure.controller;

import com.pragma.orbita.driver.routes.application.dto.RutaDto;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.routes.application.service.RutaService;
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
import java.util.List;

@Validated
@RestController
@RequestMapping("/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;

    @PostMapping
    public ResponseEntity<ObjetoRespuesta<RutaDto>> guardarRuta(@NotNull @RequestBody RutaDto rutaDto) {
        ObjetoRespuesta<RutaDto> rutaNueva = rutaService.guardarRuta(rutaDto);

        return rutaNueva.getDato() == null
                ? new ResponseEntity<>(rutaNueva, HttpStatus.CONFLICT)
                : new ResponseEntity<>(rutaNueva, HttpStatus.CREATED);
    }

    @GetMapping("/{idRuta}")
    public ResponseEntity<ObjetoRespuesta<RutaDto>> buscarRutaPorId(@NotNull @PathVariable int idRuta) {
        ObjetoRespuesta<RutaDto> ruta = rutaService.buscarRutaPorId(idRuta);

        return ruta.getDato() == null
                ? new ResponseEntity<>(ruta, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(ruta, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<List<RutaDto>>> buscarRutasPorIdUsuario(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuesta<List<RutaDto>> rutaList = rutaService.buscarRutasPorIdUsuario(idUsuario);

        return rutaList.getDato() == null
                ? new ResponseEntity<>(rutaList, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(rutaList, HttpStatus.OK);
    }
}
