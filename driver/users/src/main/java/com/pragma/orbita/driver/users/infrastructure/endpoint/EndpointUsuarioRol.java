package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioRolService;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
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

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("user-rol")
@Validated
@RequiredArgsConstructor
@Transactional
public class EndpointUsuarioRol {

    private final UsuarioRolService usuarioRolService;

    @PostMapping
    public ResponseEntity<ObjetoRespuestaDomain<UsuarioRolDtoRespuesta>> guardarUsuario(@NotNull @RequestBody UsuarioDtoConsulta usuarioDto) {
        ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> usuario = usuarioRolService.guardarUsuario(usuarioDto);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuestaDomain<UsuarioRolDtoRespuesta>> obtenerUsuarioPorId(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> usuario = usuarioRolService.buscarUsuarioPorId(idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
