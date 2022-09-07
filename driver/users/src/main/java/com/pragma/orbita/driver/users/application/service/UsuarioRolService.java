package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final UsuarioRolUseCase usuarioRolUseCase;

    private final UsuarioUseCase usuarioUseCase;

    private final RolUseCase rolUseCase;

    public ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> guardarUsuario(UsuarioDtoConsulta usuarioDtoConsulta) {
        int rol = usuarioDtoConsulta.getIdRol();
        if (!rolUseCase.existeRolById(rol))
            return new ObjetoRespuestaDomain<>(null, "No existe el rol que quiere asociar");

        Usuario usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(usuarioDtoConsulta);
        ObjetoRespuestaDomain<Usuario> respuestaUsuario = usuarioUseCase.guardarUsuario(usuario);
        if (respuestaUsuario.getDato() == null)
            return new ObjetoRespuestaDomain<>(null, respuestaUsuario.getMessage());

        UsuarioRol usuarioRol = UsuarioRol.builder()
                .idUsuario(respuestaUsuario.getDato().getIdUsuario())
                .idRol(rol)
                .build();
        ObjetoRespuestaDomain<UsuarioRol> respuestaUsuarioRol = usuarioRolUseCase.guardarUsuarioRol(usuarioRol);
        if (respuestaUsuarioRol.getDato() == null)
            return new ObjetoRespuestaDomain<>(null, respuestaUsuarioRol.getMessage());

        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(IUsuarioMapper.INSTANCE.
                        usuarioToDtoRespuesta(respuestaUsuario.getDato()))
                .rol(IRolMapper.INSTANCE
                        .rolToDtoRespuesta(rolUseCase.getRolById(rol).getDato()))
                .build();

        return  new ObjetoRespuestaDomain<>(usuarioRolDtoRespuesta, respuestaUsuarioRol.getMessage());
    }

    public ObjetoRespuestaDomain<UsuarioRolDtoRespuesta> buscarUsuarioPorId(int idUsuario) {
        ObjetoRespuestaDomain<UsuarioRol> usuarioRol = usuarioRolUseCase.obtenerPorUsuario(idUsuario);
        if (usuarioRol.getDato() == null)
            return new ObjetoRespuestaDomain<>(null, usuarioRol.getMessage());

        ObjetoRespuestaDomain<Usuario> respuestaUsuario = usuarioUseCase.getUsuarioById(usuarioRol.getDato().getIdUsuario());
        ObjetoRespuestaDomain<Rol> respuestaRol = rolUseCase.getRolById(usuarioRol.getDato().getIdRol());

        UsuarioRolDtoRespuesta usuarioRolDtoRespuesta = UsuarioRolDtoRespuesta.builder()
                .usuario(IUsuarioMapper.INSTANCE.
                        usuarioToDtoRespuesta(respuestaUsuario.getDato()))
                .rol(IRolMapper.INSTANCE
                        .rolToDtoRespuesta(respuestaRol.getDato()))
                .build();

        return new ObjetoRespuestaDomain<>(usuarioRolDtoRespuesta, "Usuario Encontrado");
    }

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> actualizarUsuario(UsuarioDtoConsulta usuarioDTOConsulta) {
        Usuario usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(usuarioDTOConsulta);
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.guardarUsuario(usuario);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, "Ocurrió un error al actualizar los datos de la categoría")
                : new ObjetoRespuestaDomain<>(
                IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                "Categoría actualizada con éxito");
    }

    public ObjetoRespuestaDomain<Integer> eliminarUsuarioById(int idUsuario) {
        ObjetoRespuestaDomain<Integer> respuesta = usuarioUseCase.eliminarUsuarioById(idUsuario);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(idUsuario, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<List<UsuarioDTORespuesta>> obtenerTodasUsuarios() {
        Stream<Usuario> usuarioStream = usuarioUseCase.obtenerTodasUsuarios().getDato();

        List<UsuarioDTORespuesta> usuarios = usuarioStream
                .map(IUsuarioMapper.INSTANCE::usuarioToDtoRespuesta)
                .collect(Collectors.toList());

        return new ObjetoRespuestaDomain<>(usuarios, "Listado");
    }
}
