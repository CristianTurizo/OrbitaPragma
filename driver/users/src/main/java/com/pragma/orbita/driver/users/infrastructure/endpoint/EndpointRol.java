package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.application.service.RolService;
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
@RequestMapping("rol")
@Validated
@RequiredArgsConstructor
public class EndpointRol {

    private final RolService rolService;

    @PostMapping
    public ResponseEntity<ObjetoRespuesta<RolDtoRespuesta>> guardarRol(@NotNull @RequestBody RolDtoConsulta rolDTOConsulta) {
        ObjetoRespuesta<RolDtoRespuesta> rol = rolService.guardarRol(rolDTOConsulta);

        return rol.getDato() == null
                ? new ResponseEntity<>(rol, HttpStatus.CONFLICT)
                : new ResponseEntity<>(rol, HttpStatus.CREATED);
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<ObjetoRespuesta<RolDtoRespuesta>> buscarRolPorId(@NotNull @PathVariable int idRol) {
        ObjetoRespuesta<RolDtoRespuesta> rol = rolService.buscarRolPorId(idRol);

        return rol.getDato() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<ObjetoRespuesta<RolDtoRespuesta>> actualizarRol(
            @NotNull @RequestBody RolDtoConsulta rolDTOConsulta,
            @NotNull @PathVariable int idRol
    ) {
        ObjetoRespuesta<RolDtoRespuesta> rol = rolService.actualizarRol(rolDTOConsulta, idRol);

        return rol.getDato() == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<ObjetoRespuesta<Object>> eliminarRolPorId(@NotNull @PathVariable int idRol) {
        ObjetoRespuesta<Object> rol = rolService.eliminarRolById(idRol);

        return rol.getDato() == null
                ? new ResponseEntity<>(rol, HttpStatus.CONFLICT)
                : new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ObjetoRespuesta<List<RolDtoRespuesta>>> obtenerTodos() {
        ObjetoRespuesta<List<RolDtoRespuesta>> rol = rolService.obtenerTodosRol();

        return rol.getDato() == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(rol, HttpStatus.OK);
    }
}
