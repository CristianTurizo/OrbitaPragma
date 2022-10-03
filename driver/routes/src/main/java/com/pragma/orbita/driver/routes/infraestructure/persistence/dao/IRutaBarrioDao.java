package com.pragma.orbita.driver.routes.infraestructure.persistence.dao;

import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaBarrioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRutaBarrioDao extends JpaRepository<RutaBarrioEntity, Integer> {
    List<RutaBarrioEntity> findByIdRutaOrderByIndice(int idRuta);

    void deleteByIdRuta(int idRuta);

}
