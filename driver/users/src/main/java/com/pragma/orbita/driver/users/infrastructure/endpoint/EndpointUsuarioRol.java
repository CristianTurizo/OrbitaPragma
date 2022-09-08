package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("relacion")
@Validated
@RequiredArgsConstructor
public class EndpointUsuarioRol {

    private final UsuarioRolService usuarioRolService;


    @PostMapping
    public ResponseEntity<ObjetoRespuesta<UsuarioRolDtoRespuesta>> guardarRelacion(@NotNull @RequestBody UsuarioRolDtoConsulta usuarioRolDtoConsulta) {
        ObjetoRespuesta<UsuarioRolDtoRespuesta> usuario = usuarioRolService.guardarUsuarioRol(usuarioRolDtoConsulta);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<List<UsuarioRolDtoRespuesta>>> buscarRelacionPorUsuario(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuesta<List<UsuarioRolDtoRespuesta>> usuario = usuarioRolService.buscarRelacionPorUsuario(idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}/{idRol}")
    public ResponseEntity<ObjetoRespuesta<Object>> eliminarUsuario(
            @NotNull @PathVariable int idUsuario,
            @NotNull @PathVariable int idRol
    ) {
        ObjetoRespuesta<Object> relacion = usuarioRolService.eliminarRelacion(idUsuario, idRol);

        return relacion.getDato() == null
                ? new ResponseEntity<>(relacion, HttpStatus.CONFLICT)
                : new ResponseEntity<>(relacion, HttpStatus.OK);
    }

}
