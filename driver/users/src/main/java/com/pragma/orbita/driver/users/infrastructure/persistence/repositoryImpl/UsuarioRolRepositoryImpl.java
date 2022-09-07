package com.pragma.orbita.driver.users.infrastructure.persistence.repositoryImpl;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.DAO.IUsuarioRolDao;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioRolEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class UsuarioRolRepositoryImpl implements IUsuarioRolRepository {

    private final IUsuarioRolDao usuarioRolDao;

    @Override
    public UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol) {
        UsuarioRolEntity usuarioRolEntity = IMapperUsuarioRolRepository.INSTANCE.usuarioRolToEntity(usuarioRol);
        return IMapperUsuarioRolRepository.INSTANCE
                .entityToDomain(
                        usuarioRolDao.save(usuarioRolEntity));
    }

    @Override
    public Optional<UsuarioRol> obtenerPorUsuario(int id) {
        Optional<UsuarioRolEntity> respuesta = usuarioRolDao.findByIdUsuario(id);
        return respuesta.isEmpty()
                ? Optional.empty()
                : Optional.of(IMapperUsuarioRolRepository.INSTANCE
                        .entityToDomain(respuesta.get()));
    }

    @Override
    public void eliminarUsuarioRol(int id) {
        usuarioRolDao.deleteById(id);
    }


    @Override
    public Stream<UsuarioRol> obtenerTodos() {
        List<UsuarioRol> usuariosRoles = new ArrayList<>();
        usuarioRolDao.findAll().forEach(usuarioRolEntity ->
                usuariosRoles.add(IMapperUsuarioRolRepository.INSTANCE
                        .entityToDomain(usuarioRolEntity)
                )
        );
        return usuariosRoles.stream();
    }

    @Override
    public boolean existeUsuarioRol(int id) {
        return usuarioRolDao.existsById(id);
    }
}
