package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.service.RolService;
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
@RequestMapping("rol")
@Validated
@RequiredArgsConstructor
public class EndpointRol {

    private final RolService rolService;

    @PostMapping
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> guardarRol(@NotNull @RequestBody RolDTOConsulta rolDTOConsulta) {
        ObjetoRespuestaDomain<RolDTORespuesta> rol = rolService.guardarRol(rolDTOConsulta);

        return rol.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, rol.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.CREATED, rol.getDato(), rol.getMessage());
    }

    @GetMapping("/{idRol}")
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> buscarRolPorId(@NotNull @PathVariable int idRol) {
        ObjetoRespuestaDomain<RolDTORespuesta> rol = rolService.buscarRolPorId(idRol);

        return rol.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.NOT_FOUND, null, rol.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, rol.getDato(), rol.getMessage());
    }

    @PutMapping
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> actualizarRol(@NotNull @RequestBody RolDTOConsulta rolDTOConsulta) {
        ObjetoRespuestaDomain<RolDTORespuesta> rol = rolService.actualizarRol(rolDTOConsulta);

        return rol.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, rol.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, rol.getDato(), rol.getMessage());
    }

    @DeleteMapping("/{idRol}")
    public ObjetoRespuestaInfrastructure<Integer> eliminarRolPorId(@NotNull @PathVariable int idRol) {
        ObjetoRespuestaDomain<Integer> rol = rolService.eliminarRolById(idRol);

        return rol.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.CONFLICT, null, rol.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, rol.getDato(), rol.getMessage());
    }

    @GetMapping
    public ObjetoRespuestaInfrastructure<List<RolDTORespuesta>> buscarRolPorId() {
        ObjetoRespuestaDomain<List<RolDTORespuesta>> rol = rolService.obtenerTodosRol();

        return rol.getDato() == null
                ? new ObjetoRespuestaInfrastructure<>(HttpStatus.NOT_FOUND, null, rol.getMessage())
                : new ObjetoRespuestaInfrastructure<>(HttpStatus.OK, rol.getDato(), rol.getMessage());
    }
}
