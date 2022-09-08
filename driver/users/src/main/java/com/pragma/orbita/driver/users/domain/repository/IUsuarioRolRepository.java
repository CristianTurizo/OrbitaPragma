package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;

import java.util.List;

public interface IUsuarioRolRepository {

    UsuarioRol guardarRelacion(UsuarioRol usuarioRol);

    List<UsuarioRol> obtenerPorUsuario(int id);

    UsuarioRol obtenerPorUsuarioYRol(int idUsuario, int idRol);

    void eliminarRelacion(int id);

    boolean existeRelacion(int id);
}
