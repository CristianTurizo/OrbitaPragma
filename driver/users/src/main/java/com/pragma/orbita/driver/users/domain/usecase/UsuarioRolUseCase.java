package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioRolUseCase {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final Validator validator;


    public UsuarioRol guardarRelacion(UsuarioRol usuarioRol) {
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
            return Collections.emptyList();

        return usuarioRolRepository.obtenerPorUsuario(idUsuario);
    }

    public UsuarioRol obtenerRelacionPorIds(int idUsuario, int idRol) {
        if (idUsuario <= 0 || idRol <= 0)
            return null;

        return usuarioRolRepository.obtenerPorUsuarioYRol(idUsuario, idRol);
    }

    public void eliminarPorId(int id) {
        usuarioRolRepository.eliminarRelacion(id);
    }

    public boolean exiteRelacion(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return usuarioRolRepository.existeUsuarioRol(idUsuario);
    }

}
