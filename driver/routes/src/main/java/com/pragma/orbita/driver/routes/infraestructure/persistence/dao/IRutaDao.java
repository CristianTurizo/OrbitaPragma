package com.pragma.orbita.driver.routes.infraestructure.persistence.dao;

import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.RutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRutaDao extends JpaRepository<RutaEntity, Integer> {

    List<RutaEntity> findByIdUsuario(int idUsuario);
}
