package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioRolMapper;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioRolUseCase;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final IUsuarioRolMapper usuarioRolMapper;
    private final UsuarioRolUseCase usuarioRolUseCase;
    private final UsuarioUseCase usuarioUseCase;
    private final RolUseCase rolUseCase;


    public ObjetoRespuesta<UsuarioRolDtoRespuesta> guardarUsuarioRol(UsuarioRolDtoConsulta usuarioRolDtoConsulta) {
        if (!usuarioUseCase.existeUsuarioById(usuarioRolDtoConsulta.getIdUsuario()))
            return new ObjetoRespuesta<>(null, "No existe el usuario");
        if (!rolUseCase.existeRolById(usuarioRolDtoConsulta.getIdRol()))
            return new ObjetoRespuesta<>(null, "No existe el rol");


        UsuarioRol usuarioRol = usuarioRolMapper.consultaDtoToUsuarioRol(usuarioRolDtoConsulta);
        UsuarioRol relacion = usuarioRolUseCase.guardarRelacion(usuarioRol);

        return relacion == null
                ? new ObjetoRespuesta<>(null, "No se pudo guardar la relacion")
                : new ObjetoRespuesta<>(
                        usuarioRolMapper.usuarioRolToRespuesta(relacion),
                        "Relacion guardada con exito");
    }

    public ObjetoRespuesta<List<UsuarioRolDtoRespuesta>> buscarRelacionPorUsuario(int idUsuario) {
        List<UsuarioRol> relacion = usuarioRolUseCase.obtenerPorUsuario(idUsuario);

        return relacion.isEmpty()
                ? new ObjetoRespuesta<>(null, "No se encontró la relacion")
                : new ObjetoRespuesta<>(
                        usuarioRolMapper.relacionesToRelacionesRespuesta(relacion),
                        "Relacion encontrado");
    }

    public ObjetoRespuesta<Object> eliminarRelacion(int idUsuario, int idRol) {
        UsuarioRol relacion = usuarioRolUseCase.obtenerRelacionPorIds(idUsuario, idRol);
        if (relacion == null)
            return new ObjetoRespuesta<>(null, "La relacion no existe");

        Integer respuesta = usuarioRolUseCase.eliminarPorId(relacion.getIdUsuarioRol());

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se pudo eliminar la relacion")
                : new ObjetoRespuesta<>(idRol, "Eliminado con exito");
    }

}
