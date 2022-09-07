package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioService;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.infrastructure.respuesta.ObjetoRespuestaInfrastructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("user")
@Validated
@RequiredArgsConstructor
public class EndpointUsuario {

    private final UsuarioService usuarioService;

    @PostMapping
    public ObjetoRespuestaInfrastructure<UsuarioDTORespuesta> guardarUsuario(@NotNull @RequestBody UsuarioDtoConsulta usuarioDTOConsulta) {
        ObjetoRespuestaDomain<UsuarioDTORespuesta> usuario = usuarioService.guardarUsuario(usuarioDTOConsulta);

        return usuario.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, usuario.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.CREATED, usuario.getDato(), usuario.getMessage());
    }

    @GetMapping("/{idUsuario}")
    public ObjetoRespuestaInfrastructure<UsuarioDTORespuesta> obtenerUsuarioPorId(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuestaDomain<UsuarioDTORespuesta> usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        return usuario.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.NOT_FOUND, null, usuario.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, usuario.getDato(), usuario.getMessage());
    }

    @PutMapping
    public ObjetoRespuestaInfrastructure<UsuarioDTORespuesta> editarUsuario(@NotNull @RequestBody UsuarioDtoConsulta usuarioDTOConsulta) {
        ObjetoRespuestaDomain<UsuarioDTORespuesta> usuario = usuarioService.actualizarUsuario(usuarioDTOConsulta);

        return usuario.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, usuario.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, usuario.getDato(), usuario.getMessage());
    }

    @DeleteMapping("/{idUsuario}")
    public ObjetoRespuestaInfrastructure<Integer> eliminarUsuario(@NotNull @PathVariable int idUsuario) {
        ObjetoRespuestaDomain<Integer> usuario = usuarioService.eliminarUsuarioById(idUsuario);

        return usuario.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, usuario.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, usuario.getDato(), usuario.getMessage());
    }

    @GetMapping
    public ObjetoRespuestaInfrastructure<List<UsuarioDTORespuesta>> obtenerTodos() {
        ObjetoRespuestaDomain<List<UsuarioDTORespuesta>> usuarios = usuarioService.obtenerTodasUsuarios();

        return usuarios.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.NOT_FOUND, null, usuarios.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.FOUND, usuarios.getDato(), usuarios.getMessage());
    }

}
