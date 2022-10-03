package com.pragma.orbita.driver.routes.domain.repository;

import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;

import java.util.List;

public interface IRutaBarrioRepository {

    RutaBarrio guardarRelacion(RutaBarrio rutaBarrio);

    List<RutaBarrio> buscarPorRuta(int idRuta);

    boolean existeRelacion(int idRelacion);

    boolean existeRelacionPorIdRuta(int idRuta);

    void eliminarRelacionPorId(int idRelacion);

    void eliminarRelacionesPorIdRuta(int idRuta);

}
