package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDtoRespuesta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
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

    private final IUsuarioMapper usuarioMapper;
    private final IRolMapper rolMapper;
    private final UsuarioRolUseCase usuarioRolUseCase;
    private final UsuarioUseCase usuarioUseCase;
    private final RolUseCase rolUseCase;

    public ObjetoRespuesta<UsuarioRolDtoRespuesta> guardarUsuarioRol(UsuarioDtoConsulta usuarioDtoConsulta) {
        List<Integer> roles = usuarioDtoConsulta.getRoles();
        for (Integer rol : roles) {
            if (!rolUseCase.existeRolById(rol))
                return new ObjetoRespuesta<>(null, "No existe el rol que quiere asociar");
        }

        Usuario respuestaUsuario = usuarioUseCase.guardarUsuario(
                usuarioMapper.consultaDtoToUsuario(usuarioDtoConsulta));
        if (respuestaUsuario == null)
            return new ObjetoRespuesta<>(null, "No se pudo guardar el usuario - Entidad");

        for (Integer rol : roles) {
            UsuarioRol usuarioRol = usuarioRolUseCase.guardarRelacion(
                    UsuarioRol.builder()
                            .idUsuario(respuestaUsuario.getIdUsuario())
                            .idRol(rol)
                            .build());

            if (usuarioRol == null)
                return new ObjetoRespuesta<>(null, "No se pudo guardar el usuario - Entidad");
        }

        List<RolDtoRespuesta> rolesDtoRespuesta = new ArrayList<>();
        for (Integer rol : roles) {
            rolesDtoRespuesta.add(
                    rolMapper.rolToDtoRespuesta(
                            rolUseCase.getRolById(rol)));
        }
        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(usuarioMapper.usuarioToDtoRespuesta(respuestaUsuario))
                .roles(rolesDtoRespuesta)
                .build();

        return new ObjetoRespuesta<>(usuarioRolDtoRespuesta, "Creado con exito");
    }

    public ObjetoRespuesta<UsuarioRolDtoRespuesta> buscarUsuarioPorId(int idUsuario) {
        Usuario respuestaUsuario = usuarioUseCase.getUsuarioById(idUsuario);
        if (respuestaUsuario == null)
            return new ObjetoRespuesta<>(null, "No se encontro usuario");

        List<UsuarioRol> rolesDeUsuario = usuarioRolUseCase.obtenerPorUsuario(respuestaUsuario.getIdUsuario());

        List<RolDtoRespuesta> rolesDtoRespuesta = new ArrayList<>();
        for (UsuarioRol rol : rolesDeUsuario) {
            rolesDtoRespuesta.add(
                    rolMapper.rolToDtoRespuesta(
                            rolUseCase.getRolById(rol.getIdRol())));
        }
        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(usuarioMapper.usuarioToDtoRespuesta(respuestaUsuario))
                .roles(rolesDtoRespuesta)
                .build();

        return new ObjetoRespuesta<>(usuarioRolDtoRespuesta, "Usuario Encontrado");
    }

    public ObjetoRespuesta<Integer> eliminarRelacion(int idUsuario, int idRol) {
        UsuarioRol relacion = usuarioRolUseCase.obtenerRelacionPorIds(idUsuario, idRol);
        if (relacion == null)
            return new ObjetoRespuesta<>(idRol, "El usuario no tiene el rol asocioado");

        usuarioRolUseCase.eliminarPorId(relacion.getIdUsuarioRol());
        return usuarioRolUseCase.exiteRelacion(relacion.getIdRol())
                ? new ObjetoRespuesta<>(idRol, "No se pudo eliminar el rol")
                : new ObjetoRespuesta<>(idRol, "Rol borrado del usuario exitosamente");
    }

    public ObjetoRespuesta<UsuarioRolDtoRespuesta> actualizarUsuarioRol(UsuarioDtoConsulta usuarioDtoConsulta) {
        List<Integer> roles = usuarioDtoConsulta.getRoles();
        for (Integer rol : roles) {
            if (!rolUseCase.existeRolById(rol))
                return new ObjetoRespuesta<>(null, "No existe el rol que quiere asociar");
        }

        Usuario respuestaUsuario = usuarioUseCase.guardarUsuario(
                usuarioMapper.consultaDtoToUsuario(usuarioDtoConsulta));
        if (respuestaUsuario == null)
            return new ObjetoRespuesta<>(null, "No se pudo guardar el usuario - Entidad");

        for (Integer rol : roles) {
            UsuarioRol relacion = usuarioRolUseCase.obtenerRelacionPorIds(respuestaUsuario.getIdUsuario(), rol);
            if (relacion != null)
                break;

            UsuarioRol usuarioRol = usuarioRolUseCase.guardarRelacion(
                    UsuarioRol.builder()
                            .idUsuario(respuestaUsuario.getIdUsuario())
                            .idRol(rol)
                            .build());

            if (usuarioRol == null)
                return new ObjetoRespuesta<>(null, "No se pudo guardar el usuario - Entidad");
        }

        List<RolDtoRespuesta> rolesDtoRespuesta = new ArrayList<>();
        for (Integer rol : roles) {
            rolesDtoRespuesta.add(
                    rolMapper.rolToDtoRespuesta(
                            rolUseCase.getRolById(rol)));
        }
        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(usuarioMapper.usuarioToDtoRespuesta(respuestaUsuario))
                .roles(rolesDtoRespuesta)
                .build();

        return new ObjetoRespuesta<>(usuarioRolDtoRespuesta, "Creado con exito");
    }
}
