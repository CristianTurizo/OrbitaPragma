package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.Usuario;

import java.util.Optional;
import java.util.stream.Stream;

public interface IUsuarioRepository {

    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> buscarUsuarioPorId(int idUsuario);

    void eliminarUsuarioById(int idUsuario);

    boolean existeUsuarioById(int idUsuario);

    Stream<Usuario> obtenerTodosUsuarios();

}
