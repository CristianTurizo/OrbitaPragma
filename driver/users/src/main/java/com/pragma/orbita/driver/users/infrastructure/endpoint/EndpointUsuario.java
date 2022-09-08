package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDtoRespuesta;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioService;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("usuario")
@Validated
@RequiredArgsConstructor
public class EndpointUsuario {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ObjetoRespuesta<UsuarioDtoRespuesta>> guardarUsuario(@NotNull @RequestBody UsuarioDtoConsulta usuarioDTOConsulta) {
        ObjetoRespuesta<UsuarioDtoRespuesta> usuario = usuarioService.guardarUsuario(usuarioDTOConsulta);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<UsuarioDtoRespuesta>> buscarUsuarioPorId(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuesta<UsuarioDtoRespuesta> usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<UsuarioDtoRespuesta>> actualizarUsuario(
            @NotNull @RequestBody UsuarioDtoConsulta usuarioDTOConsulta,
            @NotNull @PathVariable int idUsuario
    ) {
        ObjetoRespuesta<UsuarioDtoRespuesta> usuario = usuarioService.actualizarUsuario(usuarioDTOConsulta, idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<ObjetoRespuesta<Object>> eliminarUsuarioPorId(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuesta<Object> usuario = usuarioService.eliminarUsuarioById(idUsuario);

        return usuario.getDato() == null
                ? new ResponseEntity<>(usuario, HttpStatus.CONFLICT)
                : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ObjetoRespuesta<List<UsuarioDtoRespuesta>>> obtenerTodos() {
        ObjetoRespuesta<List<UsuarioDtoRespuesta>> usuarios = usuarioService.obtenerTodasUsuarios();

        return usuarios.getDato() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}
