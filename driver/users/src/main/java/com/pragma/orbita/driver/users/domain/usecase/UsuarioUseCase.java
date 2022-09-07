package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final Validator validator;

    public Usuario guardarUsuario(Usuario usuario) {
        Set<ConstraintViolation<Usuario>> validation = validator.validate(usuario);
        if (!validation.isEmpty()) {
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return null;
        }

        return usuarioRepository.guardarUsuario(usuario);
    }

    public Usuario getUsuarioById(int idUsuario) {
        if (idUsuario <= 0)
            return null;

        Optional<Usuario> usuario = usuarioRepository.getUsuarioById(idUsuario);
        return usuario.isEmpty()
                ? null
                : usuario.get();
    }

    public boolean existeUsuarioById(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return usuarioRepository.existeUsuarioById(idUsuario);
    }

    public ObjetoRespuesta<Integer> eliminarUsuarioById(int idUsuario) {
        if (idUsuario <= 0)
            return new ObjetoRespuesta<>(null, "Id no válido");

        if (!existeUsuarioById(idUsuario))
            return new ObjetoRespuesta<>(idUsuario, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");

        usuarioRepository.eliminarUsuarioById(idUsuario);

        return existeUsuarioById(idUsuario)
                ? new ObjetoRespuesta<>(idUsuario, "Ocurrió un error al eliminar la categoría")
                : new ObjetoRespuesta<>(idUsuario, "Categoría eliminada con éxito");

    }

    public ObjetoRespuesta<Stream<Usuario>> obtenerTodasUsuarios() {
        return new ObjetoRespuesta<>(
                usuarioRepository.obtenerTodosUsuarios(),
                "Listado");
    }

}
