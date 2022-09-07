package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IUsuarioRolRepository {

    UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol);

    List<UsuarioRol> obtenerPorUsuario(int id);

    void eliminarUsuarioRol(int id);

    Stream<UsuarioRol> obtenerTodos();

    boolean existeUsuarioRol(int id);
}
