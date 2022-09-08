package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RolUseCase {

    private final IRolRepository rolRepository;
    private final Validator validator;

    public Rol guardarRol(Rol rol) {
        Set<ConstraintViolation<Rol>> validation = validator.validate(rol);
        if (!validation.isEmpty()) {
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return null;
        }

        return rolRepository.guardarRol(rol);
    }

    public Rol getRolById(int idRol) {
        if (idRol <= 0)
            return null;

        Optional<Rol> rol = rolRepository.getRolById(idRol);

        return rol.isEmpty()
                ? null
                : rol.get();
    }

    public Integer eliminarRolById(int idRol) {
        if (idRol <= 0)
            return null;
        if (!existeRolById(idRol))
            return null;

        rolRepository.eliminarRolById(idRol);

        return existeRolById(idRol)
                ? null
                : idRol;
    }

    public Stream<Rol> obtenerTodosRol() {
        return rolRepository.obtenerTodosRol();
    }

    public boolean existeRolById(int idRol) {
        return rolRepository.existeRolById(idRol);
    }

}
