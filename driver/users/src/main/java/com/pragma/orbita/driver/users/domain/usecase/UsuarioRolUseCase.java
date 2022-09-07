package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioRolUseCase {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final Validator validator;


    public UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol) {
        Set<ConstraintViolation<UsuarioRol>> validation = validator.validate(usuarioRol);
        if (!validation.isEmpty()) {
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return null;
        }

        return usuarioRolRepository.guardarUsuarioRol(usuarioRol);
    }

    public List<UsuarioRol> obtenerPorUsuario(int idUsuario) {
        if (idUsuario <= 0)
            return null;

        return usuarioRolRepository.obtenerPorUsuario(idUsuario);
    }

    public ObjetoRespuesta<Integer> eliminarUsuarioById(int idUsuario) {
        if (idUsuario <= 0)
            return new ObjetoRespuesta<>(null, "Id no válido");

        if (!existeUsuarioById(idUsuario))
            return new ObjetoRespuesta<>(idUsuario, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");

        usuarioRolRepository.eliminarUsuarioRol(idUsuario);

        return existeUsuarioById(idUsuario)
                ? new ObjetoRespuesta<>(idUsuario, "Ocurrió un error al eliminar la categoría")
                : new ObjetoRespuesta<>(idUsuario, "Categoría eliminada con éxito");
    }

    public ObjetoRespuesta<Stream<UsuarioRol>> obtenerTodasUsuarios() {
        return new ObjetoRespuesta<>(
                usuarioRolRepository.obtenerTodos(),
                "Listado");
    }

    public boolean existeUsuarioById(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return usuarioRolRepository.existeUsuarioRol(idUsuario);
    }

}
