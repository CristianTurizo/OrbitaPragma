package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
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

    public ObjetoRespuesta<Integer> eliminarRolById(int idRol) {
        if (idRol <= 0)
            return new ObjetoRespuesta<>(null, "Id no válido");

        if (!existeRolById(idRol))
            return new ObjetoRespuesta<>(idRol, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");

        rolRepository.eliminarRolById(idRol);

        return existeRolById(idRol)
                ? new ObjetoRespuesta<>(idRol, "Ocurrió un error al eliminar la categoría")
                : new ObjetoRespuesta<>(idRol, "Categoría eliminada con éxito");
    }

    public ObjetoRespuesta<Stream<Rol>> obtenerTodosRol() {
        return new ObjetoRespuesta<>(
                rolRepository.obtenerTodosRol(),
                "Listado");
    }

    public boolean existeRolById(int idRol) {
        return rolRepository.existeRolById(idRol);
    }

}
