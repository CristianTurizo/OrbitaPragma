package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
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

    public ObjetoRespuestaDomain<Rol> getRolById(int idRol) {
        if (idRol <= 0)
            return new ObjetoRespuestaDomain<>(null, "Id no válido");

        Optional<Rol> rol = rolRepository.getRolById(idRol);

        return rol.isEmpty()
                ? new ObjetoRespuestaDomain<>(null, "La rol no existe en el sistema")
                : new ObjetoRespuestaDomain<>(rol.get(), "Rol encontrado");
    }

    public ObjetoRespuestaDomain<Rol> guardarRol(Rol rol) {
        Set<ConstraintViolation<Rol>> validation = validator.validate(rol);
        if (!validation.isEmpty()) {
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return new ObjetoRespuestaDomain<>(null, errores.toString());
        }

        Rol respuesta = rolRepository.guardarRol(rol);

        return respuesta == null
                ? new ObjetoRespuestaDomain<>(null, "Ocurrió un error al guardar el rol")
                : new ObjetoRespuestaDomain<>(respuesta, "Rol agregado con éxito, id: " + respuesta.getIdRol());
    }


    public ObjetoRespuestaDomain<Object> eliminarRolById(int idRol) {
        if (idRol <= 0)
            return new ObjetoRespuestaDomain<>(null, "Id no válido");

        if (!existRolById(idRol)) {
            return new ObjetoRespuestaDomain<>(idRol, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");
        }

        rolRepository.eliminarRolById(idRol);

        return existRolById(idRol)
                ? new ObjetoRespuestaDomain<>(idRol, "Ocurrió un error al eliminar la categoría")
                : new ObjetoRespuestaDomain<>(idRol, "Categoría eliminada con éxito");
    }

    public ObjetoRespuestaDomain<Stream<Rol>> obtenerTodosRol() {
        return new ObjetoRespuestaDomain<>(
                rolRepository.obtenerTodosRol(),
                "Listado");
    }

    private boolean existRolById(int idRol) {
        return rolRepository.existeRolById(idRol);
    }

}
