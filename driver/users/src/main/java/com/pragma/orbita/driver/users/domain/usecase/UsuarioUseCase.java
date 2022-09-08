package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.guardarUsuario(usuario);
    }

    public Usuario buscarUsarioPorId(int idUsuario) {
        if (idUsuario <= 0)
            return null;

        Optional<Usuario> usuario = usuarioRepository.buscarUsuarioPorId(idUsuario);
        return usuario.isEmpty()
                ? null
                : usuario.get();
    }

    public Integer eliminarUsuarioPorId(int idUsuario) {
        if (idUsuario <= 0)
            return null;
        if (!existeUsuarioById(idUsuario))
            return null;

        usuarioRepository.eliminarUsuarioById(idUsuario);

        return existeUsuarioById(idUsuario)
                ? null
                : idUsuario;
    }

    public boolean existeUsuarioById(int idUsuario) {
        return usuarioRepository.existeUsuarioById(idUsuario);
    }

    public Stream<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.obtenerTodosUsuarios();
    }

}
