package com.pragma.orbita.driver.routes.infraestructure.persistence.repositoryimpl;

import com.pragma.orbita.driver.routes.domain.model.Barrio;
import com.pragma.orbita.driver.routes.domain.repository.IBarrioRepository;
import com.pragma.orbita.driver.routes.infraestructure.persistence.dao.IBarrioDao;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.BarrioEntity;
import com.pragma.orbita.driver.routes.infraestructure.persistence.mapper.IMapperBarrioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BarrioRepositoryImpl implements IBarrioRepository {

    private final IMapperBarrioRepository mapperBarrioRepository;
    private final IBarrioDao barrioDao;

    @Override
    public Barrio guardarBarrio(Barrio barrio) {
        BarrioEntity barrioEntity = mapperBarrioRepository.toEntity(barrio);
        return mapperBarrioRepository.toDomain(
                barrioDao.save(barrioEntity));
    }

    @Override
    public Barrio buscarBarrioPorNombre(String nombreBarrio) {
        return mapperBarrioRepository.toDomain(
                barrioDao.findByNombre(nombreBarrio));
    }

    @Override
    public Barrio buscarBarrioPorId(int idBarrio) {
        Optional<BarrioEntity> barrioEntity = barrioDao.findById(idBarrio);
        return barrioEntity.isEmpty()
                ? null
                : mapperBarrioRepository.toDomain(barrioEntity.get());
    }
}
