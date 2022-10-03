package com.pragma.orbita.driver.routes.domain.usecase;

import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;
import com.pragma.orbita.driver.routes.domain.repository.IRutaBarrioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaBarrioUseCase {

    private final IRutaBarrioRepository rutaBarrioRepository;

    public RutaBarrio guardarRelacion(RutaBarrio relacion) {
        return rutaBarrioRepository.guardarRelacion(relacion);
    }

    public List<RutaBarrio> buscarPorIdRuta(int idRuta) {
        if (idRuta < 0) {
            return List.of();
        }
        return rutaBarrioRepository.buscarPorRuta(idRuta);
    }

    public Integer eliminarRelacionPorId(int idRelacion) {
        if (idRelacion <= 0) {
            return null;
        }
        if (!existeRelacion(idRelacion)) {
            return null;
        }

        rutaBarrioRepository.eliminarRelacionPorId(idRelacion);

        return existeRelacion(idRelacion)
                ? null
                : idRelacion;
    }

    public Integer eliminarRelacionesPorRuta(int idRuta) {
        if (idRuta <= 0) {
            return null;
        }
        rutaBarrioRepository.eliminarRelacionesPorIdRuta(idRuta);
        return idRuta;
    }

    public boolean existeRelacion(int idRelacion) {
        return rutaBarrioRepository.existeRelacion(idRelacion);
    }

    public boolean existenRelacionesPorRuta(int idRelacion) {
        return rutaBarrioRepository.existeRelacionPorIdRuta(idRelacion);
    }

}
