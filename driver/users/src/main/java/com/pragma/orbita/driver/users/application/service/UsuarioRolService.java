package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDtoRespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioRolUseCase;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final IUsuarioMapper usuarioMapper;
    private final IRolMapper rolMapper;
    private final UsuarioRolUseCase usuarioRolUseCase;
    private final UsuarioUseCase usuarioUseCase;
    private final RolUseCase rolUseCase;

    public ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> guardarUsuario(UsuarioDtoConsulta usuarioDtoConsulta) {
        int rol = usuarioDtoConsulta.getIdRol();
        if (!rolUseCase.existeRolById(rol))
            return new ObjetoRespuestaDomain<>(null, "No existe el rol que quiere asociar");

        Usuario respuestaUsuario = usuarioUseCase.guardarUsuario(
                usuarioMapper.consultaDtoToUsuario(usuarioDtoConsulta));
        if (respuestaUsuario == null)
            return new ObjetoRespuestaDomain<>(null, "No se pudo guardar el usuario - Entidad");

        UsuarioRol usuarioRol = UsuarioRol.builder()
                .idUsuario(respuestaUsuario.getIdUsuario())
                .idRol(rol)
                .build();

        UsuarioRol respuestaUsuarioRol = usuarioRolUseCase.guardarUsuarioRol(usuarioRol);
        if (respuestaUsuarioRol == null)
            return new ObjetoRespuestaDomain<>(null, "No se pudo guardar el usuario - Relacion");

        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(usuarioMapper.usuarioToDtoRespuesta(respuestaUsuario))
                .rol(rolMapper.rolToDtoRespuesta(rolUseCase.getRolById(rol)))
                .build();

        return new ObjetoRespuestaDomain<>(usuarioRolDtoRespuesta, "Creado con exito");
    }

    public ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> buscarUsuarioPorId(int idUsuario) {
        Usuario respuestaUsuario = usuarioUseCase.getUsuarioById(idUsuario);
        if (respuestaUsuario == null)
            return new ObjetoRespuestaDomain<>(null, "No se encontro usuario");

        UsuarioRol usuarioRol = usuarioRolUseCase.obtenerPorUsuario(respuestaUsuario.getIdUsuario());
        Rol respuestaRol = rolUseCase.getRolById(usuarioRol.getIdRol());

        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(usuarioMapper.usuarioToDtoRespuesta(respuestaUsuario))
                .rol(rolMapper.rolToDtoRespuesta(respuestaRol))
                .build();

        return new ObjetoRespuestaDomain<>(usuarioRolDtoRespuesta, "Usuario Encontrado");
    }

}
