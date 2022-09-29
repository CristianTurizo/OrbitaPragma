package com.pragma.orbita.driver.routes.domain.usecase;

import com.pragma.orbita.driver.routes.domain.model.Ruta;
import com.pragma.orbita.driver.routes.domain.repository.IRutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaUseCase {

    private final IRutaRepository rutaRepository;


    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepository.guardarRuta(ruta);
    }

    public Ruta buscarPorIdRuta(int idRuta) {
        if (idRuta < 0) {
            return null;
        }
        return rutaRepository.buscarPorId(idRuta);
    }

    public List<Ruta> buscarPorIdUsuario(int idUsuario) {
        if (idUsuario < 0) {
            return List.of();
        }
        return rutaRepository.buscarPorIdUsuario(idUsuario);
    }
}
