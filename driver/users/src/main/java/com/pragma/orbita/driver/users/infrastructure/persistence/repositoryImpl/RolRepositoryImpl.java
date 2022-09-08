package com.pragma.orbita.driver.users.infrastructure.persistence.repositoryImpl;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.DAO.IRolDao;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.RolEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class RolRepositoryImpl implements IRolRepository {

    private final IMapperRolRepository mapperRolRepository;
    private final IRolDao rolDao;

    @Override
    public Rol guardarRol(Rol rol) {
        RolEntity rolEntity = mapperRolRepository.domainToEntity(rol);
        return mapperRolRepository
                .entityToDomain(
                        rolDao.save(rolEntity));
    }

    @Override
    public Optional<Rol> buscarRolPorId(int idRol) {
        Optional<RolEntity> respuesta = rolDao.findById(idRol);
        return respuesta.isEmpty()
                ? Optional.empty()
                : Optional.of(
                        mapperRolRepository.entityToDomain(
                                respuesta.get()));
    }

    @Override
    public void eliminarRolById(int idRol) {
        rolDao.deleteById(idRol);
    }

    @Override
    public boolean existeRolById(int idRol) {
        return rolDao.existsById(idRol);
    }

    @Override
    public Stream<Rol> obtenerTodosRol() {
        List<Rol> rolList = new ArrayList<>();
        rolDao.findAll().forEach(rolEntity ->
                rolList.add(
                        mapperRolRepository.entityToDomain(rolEntity)
                ));
        return rolList.stream();
    }
}
