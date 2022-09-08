package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.Rol;

import java.util.Optional;
import java.util.stream.Stream;

public interface IRolRepository {
    Rol guardarRol(Rol rol);

    Optional<Rol> buscarRolPorId(int idRol);

    void eliminarRolById(int idRol);

    boolean existeRolById(int idRol);

    Stream<Rol> obtenerTodosRol();

}
