package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDtoRespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioMapper usuarioMapper;
    private final UsuarioUseCase usuarioUseCase;

    public ObjetoRespuesta<UsuarioDtoRespuesta> guardarUsuario(UsuarioDtoConsulta usuarioDTOConsulta) {
        Usuario usuario = usuarioMapper.consultaDtoToUsuario(usuarioDTOConsulta);
        Usuario respuesta = usuarioUseCase.guardarUsuario(usuario);

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se pudo guardar el usuario")
                : new ObjetoRespuesta<>(
                usuarioMapper.usuarioToDtoRespuesta(respuesta),
                "Usuario guardado con exito");
    }

    public ObjetoRespuesta<UsuarioDtoRespuesta> buscarUsuarioPorId(int idUsuario) {
        Usuario respuesta = usuarioUseCase.getUsuarioById(idUsuario);

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se encontró el usuario")
                : new ObjetoRespuesta<>(
                usuarioMapper.usuarioToDtoRespuesta(respuesta),
                "Usuario encontrado");
    }

    public ObjetoRespuesta<UsuarioDtoRespuesta> actualizarUsuario(UsuarioDtoConsulta usuarioDTOConsulta) {
        Usuario usuario = usuarioMapper.consultaDtoToUsuario(usuarioDTOConsulta);
        Usuario respuesta = usuarioUseCase.guardarUsuario(usuario);

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "Ocurrió un error al actualizar los datos de la categoría")
                : new ObjetoRespuesta<>(
                usuarioMapper.usuarioToDtoRespuesta(respuesta),
                "Categoría actualizada con éxito");
    }

    public ObjetoRespuesta<Object> eliminarUsuarioById(int idUsuario) {
        Integer respuesta = usuarioUseCase.eliminarUsuarioById(idUsuario);

        return respuesta == null
                ? new ObjetoRespuesta<>(null, "No se pudo eliminar el usuario")
                : new ObjetoRespuesta<>(respuesta, "Usuario eliminado con exito");
    }

    public ObjetoRespuesta<List<UsuarioDtoRespuesta>> obtenerTodasUsuarios() {
        Stream<Usuario> usuarioStream = usuarioUseCase.obtenerTodasUsuarios();

        List<UsuarioDtoRespuesta> usuarios = usuarioStream
                .map(usuarioMapper::usuarioToDtoRespuesta)
                .collect(Collectors.toList());

        return new ObjetoRespuesta<>(usuarios, "Listado");
    }
}
