package com.pragma.orbita.driver.routes.infraestructure.persistence.repositoryimpl;

import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;
import com.pragma.orbita.driver.routes.domain.repository.IRutaBarrioRepository;
import com.pragma.orbita.driver.routes.infraestructure.persistence.dao.IRutaBarrioDao;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaBarrioEntity;
import com.pragma.orbita.driver.routes.infraestructure.persistence.mapper.IMapperRutaBarrioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RutaBarrioRopositoryImpl implements IRutaBarrioRepository {

    private final IMapperRutaBarrioRepository mapperRutaBarrioRepository;

    private final IRutaBarrioDao rutaBarrioDao;

    @Override
    public RutaBarrio guardarRelacion(RutaBarrio rutaBarrio) {
        RutaBarrioEntity entity = mapperRutaBarrioRepository.toEntity(rutaBarrio);
        return mapperRutaBarrioRepository.toDomain(
                rutaBarrioDao.save(entity));
    }

    @Override
    public List<RutaBarrio> buscarPorRuta(int idRuta) {
        return mapperRutaBarrioRepository.toDomainList(
                rutaBarrioDao.findByIdRutaOrderByIndice(idRuta));
    }

    @Override
    public boolean existeRelacion(int idRelacion) {
        return rutaBarrioDao.existsById(idRelacion);
    }

    @Override
    public boolean existeRelacionPorIdRuta(int idRuta) {
        return true;
    }

    @Override
    public void eliminarRelacionPorId(int idRelacion) {
        rutaBarrioDao.deleteById(idRelacion);
    }

    @Override
    public void eliminarRelacionesPorIdRuta(int idRuta) {
        rutaBarrioDao.deleteByIdRuta(idRuta);
    }
}
