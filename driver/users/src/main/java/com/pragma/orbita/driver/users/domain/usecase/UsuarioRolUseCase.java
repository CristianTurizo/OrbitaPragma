package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioRolUseCase {

    private final IUsuarioRolRepository usuarioRolRepository;


    public UsuarioRol guardarRelacion(UsuarioRol usuarioRol) {
        return usuarioRolRepository.guardarRelacion(usuarioRol);
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

    public Integer eliminarPorId(int idRelacion) {
        if (idRelacion <= 0)
            return null;

        usuarioRolRepository.eliminarRelacion(idRelacion);

        return existeRelacion(idRelacion)
                ? null
                : idRelacion;
    }

    private boolean existeRelacion(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return usuarioRolRepository.existeRelacion(idUsuario);
    }

}
