package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDtoConsulta;
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

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> guardarUsuario(UsuarioDtoConsulta usuarioDTOConsulta) {
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
