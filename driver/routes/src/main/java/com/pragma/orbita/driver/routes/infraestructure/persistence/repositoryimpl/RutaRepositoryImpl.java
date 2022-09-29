package com.pragma.orbita.driver.routes.infraestructure.persistence.repositoryimpl;

import com.pragma.orbita.driver.routes.domain.model.Ruta;
import com.pragma.orbita.driver.routes.domain.repository.IRutaRepository;
import com.pragma.orbita.driver.routes.infraestructure.persistence.dao.IRutaDao;
import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaEntity;
import com.pragma.orbita.driver.routes.infraestructure.persistence.mapper.IMapperRutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RutaRepositoryImpl implements IRutaRepository {

    private final IMapperRutaRepository mapperRutaRepository;

    private final IRutaDao rutaDao;

    @Override
    public Ruta guardarRuta(Ruta ruta) {
        RutaEntity rutaEntity = mapperRutaRepository.toEntity(ruta);
        return mapperRutaRepository.toDomain(
                rutaDao.save(rutaEntity));
    }

    @Override
    public Ruta buscarPorId(int idRuta) {
        Optional<RutaEntity> rutaEntity = rutaDao.findById(idRuta);
        return rutaEntity.isEmpty()
                ? null
                : mapperRutaRepository.toDomain(rutaEntity.get());
    }

    @Override
    public List<Ruta> buscarPorIdUsuario(int idUsuario) {
        return mapperRutaRepository.toDomainList(
                rutaDao.findByIdUsuario(idUsuario));
    }
}
