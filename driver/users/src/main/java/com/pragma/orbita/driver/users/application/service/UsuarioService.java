package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioUseCase usuarioUseCase;

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> guardarUsuario(UsuarioDTOConsulta usuarioDTOConsulta) {
        Usuario usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(usuarioDTOConsulta);
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.guardarUsuario(usuario);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(
                IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> buscarUsuarioPorId(int idUsuario) {
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.getUsuarioById(idUsuario);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, respuesta.getMessage())
                : new ObjetoRespuestaDomain<>(
                        IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                        respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> actualizarUsuario(UsuarioDTOConsulta usuarioDTOConsulta) {
        Usuario usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(usuarioDTOConsulta);
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.guardarUsuario(usuario);

        return respuesta.getDato() == null
                ? new ObjetoRespuestaDomain<>(null, "Ocurrió un error al actualizar los datos de la categoría")
                : new ObjetoRespuestaDomain<>(
                        IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                        "Categoría actualizada con éxito");
    }

    public ObjetoRespuestaDomain<Object> eliminarUsuarioById(int idUsuario) {
        ObjetoRespuestaDomain<Object> respuesta = usuarioUseCase.eliminarUsuarioById(idUsuario);

        if (respuesta.getDato() == null) {
            return new ObjetoRespuestaDomain<>(null, respuesta.getMessage());
        }
        return new ObjetoRespuestaDomain<>(idUsuario, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<List<UsuarioDTORespuesta>> obtenerTodasUsuarios() {
        Stream<Usuario> usuarioStream = usuarioUseCase.obtenerTodasUsuarios().getDato();

        List<UsuarioDTORespuesta> usuarios = usuarioStream
                .map(IUsuarioMapper.INSTANCE::usuarioToDtoRespuesta)
                .collect(Collectors.toList());

        return new ObjetoRespuestaDomain<>(usuarios, "Listado");
    }
}
