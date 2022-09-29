package com.pragma.orbita.driver.routes.domain.repository;

import com.pragma.orbita.driver.routes.domain.model.Ruta;

import java.util.List;

public interface IRutaRepository {

    Ruta guardarRuta(Ruta ruta);

    Ruta buscarPorId(int idRuta);

    List<Ruta> buscarPorIdUsuario(int idUsuario);
}
