package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<ObjetoRespuesta<UsuarioRolDtoRespuesta>> guardarUsuario(@NotNull @RequestBody UsuarioDtoConsulta usuarioDto) {
        ObjetoRespuesta<UsuarioRolDtoRespuesta> usuario = usuarioRolService.guardarUsuarioRol(usuarioDto);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<UsuarioRolDtoRespuesta>> obtenerUsuarioPorId(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuesta<UsuarioRolDtoRespuesta> usuario = usuarioRolService.buscarUsuarioPorId(idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}/{idRol}")
    public ResponseEntity<ObjetoRespuesta<Integer>> eliminarRolDeUsuario(
            @NotNull @PathVariable int idUsuario,
            @NotNull @PathVariable int idRol
    ){
        ObjetoRespuesta<Integer> rolBorrado = usuarioRolService.eliminarRelacion(idUsuario, idRol);

        return rolBorrado.getDato() == null
                ? new ResponseEntity<>(rolBorrado, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(rolBorrado, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<UsuarioRolDtoRespuesta>> modificarUsuario(
            @NotNull @RequestBody UsuarioDtoConsulta usuarioDto,
            @NotNull @PathVariable Integer idUsuario
    ) {
        usuarioDto.setIdUsuario(idUsuario);
        ObjetoRespuesta<UsuarioRolDtoRespuesta> usuario = usuarioRolService.actualizarUsuarioRol(usuarioDto);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }


}
