package com.pragma.orbita.driver.routes.infraestructure.persistence.dao;

import com.pragma.orbita.driver.routes.infraestructure.persistence.entity.BarrioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarrioDao extends JpaRepository<BarrioEntity, Integer> {

    BarrioEntity findByNombre(String nombre);
}
