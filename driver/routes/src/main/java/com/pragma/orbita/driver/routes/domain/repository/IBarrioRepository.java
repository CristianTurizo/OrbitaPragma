package com.pragma.orbita.driver.routes.domain.repository;

import com.pragma.orbita.driver.routes.domain.model.Barrio;

public interface IBarrioRepository {

    Barrio guardarBarrio(Barrio barrio);

    Barrio buscarBarrioPorNombre(String nombreBarrio);
}
