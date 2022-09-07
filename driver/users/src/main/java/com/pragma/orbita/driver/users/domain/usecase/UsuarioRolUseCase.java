package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
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
public class UsuarioRolUseCase {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final Validator validator;


    public ObjetoRespuestaDomain<UsuarioRol> guardarUsuarioRol(UsuarioRol usuarioRol) {
        Set<ConstraintViolation<UsuarioRol>> validation = validator.validate(usuarioRol);
        if (!validation.isEmpty()) {
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return new ObjetoRespuestaDomain<>(null, errores.toString());
        }

        UsuarioRol respuesta = usuarioRolRepository.guardarUsuarioRol(usuarioRol);
        return respuesta == null
                ? new ObjetoRespuestaDomain<>(null, "Ocurrió un error al guardar la categoría")
                : new ObjetoRespuestaDomain<>(respuesta, "Registro exitoso, id: " + respuesta.getIdUsuario());
    }

    public ObjetoRespuestaDomain<UsuarioRol> obtenerPorUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return new ObjetoRespuestaDomain<>(null, "Id no válido");
        }

        Optional<UsuarioRol> usuario = usuarioRolRepository.obtenerPorUsuario(idUsuario);
        return usuario.isEmpty()
                ? new ObjetoRespuestaDomain<>(null, "La Usuario no existe en el sistema")
                : new ObjetoRespuestaDomain<>(usuario.get(), "Usuario encontrada");
    }

    public ObjetoRespuestaDomain<Integer> eliminarUsuarioById(int idUsuario) {
        if (idUsuario <= 0)
            return new ObjetoRespuestaDomain<>(null, "Id no válido");

        if (!existeUsuarioById(idUsuario))
            return new ObjetoRespuestaDomain<>(idUsuario, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");

        usuarioRolRepository.eliminarUsuarioRol(idUsuario);

        return existeUsuarioById(idUsuario)
                ? new ObjetoRespuestaDomain<>(idUsuario, "Ocurrió un error al eliminar la categoría")
                : new ObjetoRespuestaDomain<>(idUsuario, "Categoría eliminada con éxito");
    }

    public ObjetoRespuestaDomain<Stream<UsuarioRol>> obtenerTodasUsuarios() {
        return new ObjetoRespuestaDomain<>(
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
