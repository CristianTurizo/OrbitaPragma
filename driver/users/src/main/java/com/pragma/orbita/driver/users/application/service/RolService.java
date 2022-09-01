package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RolService {

    private RolUseCase rolUseCase;

    public ObjetoRespuestaDomain<RolDTORespuesta> guardarRol(RolDTOConsulta rolDTOConsulta) {
        Rol rol = IRolMapper.INSTANCE.consultaDtoToRol(rolDTOConsulta);
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.guardarRol(rol);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(
                        IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato()),
                        respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<RolDTORespuesta> buscarRolPorId(int idRol) {
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.getRolById(idRol);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(
                        IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato()),
                        respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<RolDTORespuesta> actualizarRol(RolDTOConsulta rolDTOConsulta) {
        Rol rol = IRolMapper.INSTANCE.consultaDtoToRol(rolDTOConsulta);
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.guardarRol(rol);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, "Ocurrió un error al actualizar los datos del rol")
                : new ObjetoRespuestaDomain<>(
                        IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato()),
                        "Rol actualizado con éxito");
    }

    public ObjetoRespuestaDomain<Object> eliminarRolById(int idRol) {
        ObjetoRespuestaDomain<Object> respuesta = rolUseCase.eliminarRolById(idRol);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(idRol, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<List<RolDTORespuesta>> obtenerTodosRol() {
        Stream<Rol> rolStream = rolUseCase.obtenerTodosRol().getDato();

        List<RolDTORespuesta> rols = rolStream
                .map(IRolMapper.INSTANCE::rolToDtoRespuesta)
                .collect(Collectors.toList());

        return new ObjetoRespuestaDomain<>(rols, "Listado");
    }
}
