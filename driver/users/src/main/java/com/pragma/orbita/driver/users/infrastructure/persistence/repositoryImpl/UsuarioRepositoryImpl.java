package com.pragma.orbita.driver.users.infrastructure.persistence.repositoryImpl;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.DAO.IUsuarioDao;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final IMapperUsuarioRepository mapperUsuarioRepository;
    private final IUsuarioDao usuarioDao;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = mapperUsuarioRepository.domainToEntity(usuario);
        return mapperUsuarioRepository
                .entityToDomain(
                        usuarioDao.save(usuarioEntity));
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(int idUsuario) {
        Optional<UsuarioEntity> respuesta = usuarioDao.findById(idUsuario);
        return respuesta.isEmpty()
                ? Optional.empty()
                : Optional.of(
                        mapperUsuarioRepository.entityToDomain(
                                respuesta.get()));
    }

    @Override
    public void eliminarUsuarioById(int idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    public boolean existeUsuarioById(int idUsuario) {
        return usuarioDao.existsById(idUsuario);
    }

    @Override
    public Stream<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioDao.findAll().forEach(usuarioEntity ->
                usuarios.add(
                        mapperUsuarioRepository.entityToDomain(usuarioEntity)
                )
        );
        return usuarios.stream();
    }
}
