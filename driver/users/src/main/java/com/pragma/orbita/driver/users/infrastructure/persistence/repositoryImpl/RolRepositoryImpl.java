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

    private final IRolDao rolDao;

    @Override
    public Optional<Rol> getRolById(int idRol) {
        Optional<RolEntity> respuesta = rolDao.findById(idRol);
        if (respuesta.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                IMapperRolRepository.INSTANCE
                        .entityToRol(respuesta.get())
        );
    }

    @Override
    public Rol guardarRol(Rol rol) {
        RolEntity rolEntity = IMapperRolRepository.INSTANCE.rolToEntity(rol);
        return IMapperRolRepository.INSTANCE
                .entityToRol(
                        rolDao.save(rolEntity)
                );
    }

    @Override
    public Boolean existeRolById(int idRol) {
        return rolDao.existsById(idRol);
    }

    @Override
    public void eliminarRolById(int idRol) {
        rolDao.deleteById(idRol);
    }

    @Override
    public Stream<Rol> obtenerTodosRol() {
        List<Rol> rolList = new ArrayList<>();
        rolDao.findAll().forEach(rolEntity ->
                rolList.add(
                        IMapperRolRepository.INSTANCE.entityToRol(rolEntity)
                ));
        return rolList.stream();
    }
}
