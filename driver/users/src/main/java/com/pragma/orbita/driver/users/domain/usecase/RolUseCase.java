package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RolUseCase {

    private final IRolRepository rolRepository;

    public Rol guardarRol(Rol rol) {
        return rolRepository.guardarRol(rol);
    }

    public Rol buscarRolPorId(int idRol) {
        if (idRol <= 0) {
            return null;
        }

        Optional<Rol> rol = rolRepository.buscarRolPorId(idRol);
        return rol.isEmpty()
                ? null
                : rol.get();
    }

    public Integer eliminarRolPorId(int idRol) {
        if (idRol <= 0) {
            return null;
        }
        if (!existeRolById(idRol)) {
            return null;
        }

        rolRepository.eliminarRolById(idRol);

        return existeRolById(idRol)
                ? null
                : idRol;
    }

    public boolean existeRolById(int idRol) {
        return rolRepository.existeRolById(idRol);
    }

    public Stream<Rol> obtenerTodosRol() {
        return rolRepository.obtenerTodosRol();
    }

}
