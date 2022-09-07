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

    private final IMapperUsuarioRolRepository mapperUsuarioRolRepository;
    private final IUsuarioRolDao usuarioRolDao;

    @Override
    public UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol) {
        UsuarioRolEntity usuarioRolEntity = mapperUsuarioRolRepository.usuarioRolToEntity(usuarioRol);
        return mapperUsuarioRolRepository
                .entityToDomain(
                        usuarioRolDao.save(usuarioRolEntity));
    }

    @Override
    public List<UsuarioRol> obtenerPorUsuario(int id) {
        return mapperUsuarioRolRepository.entitiesToDomain(
                usuarioRolDao.findByIdUsuario(id));
    }

    @Override
    public UsuarioRol obtenerPorUsuarioYRol(int idUsuario, int idRol) {
        return mapperUsuarioRolRepository.entityToDomain(
                usuarioRolDao.findByIdUsuarioAndIdRol(idUsuario, idRol));
    }

    @Override
    public void eliminarRelacion(int id) {
        usuarioRolDao.deleteById(id);
    }

    @Override
    public boolean existeUsuarioRol(int id) {
        return usuarioRolDao.existsById(id);
    }
}
