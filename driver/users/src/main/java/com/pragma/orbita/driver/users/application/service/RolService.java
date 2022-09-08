package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RolService {

    private final IRolMapper rolMapper;
    private final RolUseCase rolUseCase;

    public ObjetoRespuesta<RolDtoRespuesta> guardarRol(RolDtoConsulta rolDTOConsulta) {
        Rol respuesta = rolUseCase.guardarRol(
                rolMapper.consultaDtoToRol(rolDTOConsulta));

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se pudo guardar el Rol")
                : new ObjetoRespuesta<>(
                        rolMapper.rolToDtoRespuesta(respuesta),
                        "Rol guardado con exito");
    }

    public ObjetoRespuesta<RolDtoRespuesta> buscarRolPorId(int idRol) {
        Rol respuesta = rolUseCase.buscarRolPorId(idRol);

        return respuesta == null
                ? new ObjetoRespuesta<>()
                : new ObjetoRespuesta<>(
                        rolMapper.rolToDtoRespuesta(respuesta),
                        "Rol encontrado");
    }

    public ObjetoRespuesta<RolDtoRespuesta> actualizarRol(RolDtoConsulta rolDTOConsulta, int idRol) {
        rolDTOConsulta.setIdRol(idRol);
        Rol respuesta = rolUseCase.guardarRol(
                rolMapper.consultaDtoToRol(rolDTOConsulta));

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "Ocurrió un error al actualizar los datos del rol")
                : new ObjetoRespuesta<>(
                rolMapper.rolToDtoRespuesta(respuesta),
                "Rol actualizado con éxito");
    }

    public ObjetoRespuesta<Object> eliminarRolById(int idRol) {
        Integer respuesta = rolUseCase.eliminarRolPorId(idRol);

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se pudo eliminar el Rol")
                : new ObjetoRespuesta<>(respuesta, "Rol eliminado con exito");
    }

    public ObjetoRespuesta<List<RolDtoRespuesta>> obtenerTodosRol() {
        Stream<Rol> rolStream = rolUseCase.obtenerTodosRol();

        List<RolDtoRespuesta> rols = rolStream
                .map(rolMapper::rolToDtoRespuesta)
                .collect(Collectors.toList());

        return new ObjetoRespuesta<>(rols, "Obtenidos todos los roles");
    }
}
