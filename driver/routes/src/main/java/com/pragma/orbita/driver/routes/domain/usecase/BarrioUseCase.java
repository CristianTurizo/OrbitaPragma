package com.pragma.orbita.driver.routes.domain.usecase;

import com.pragma.orbita.driver.routes.domain.model.Barrio;
import com.pragma.orbita.driver.routes.domain.repository.IBarrioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarrioUseCase {

    private final IBarrioRepository barrioRepository;

    public Barrio guardarBarrio(Barrio barrio) {
        return barrioRepository.guardarBarrio(barrio);
    }

    public Barrio buscarBarrioPorNombre(String nombreBarrio) {
        return barrioRepository.buscarBarrioPorNombre(nombreBarrio);
    }

    public Barrio buscarBarrioPorId(int idBarrio){
        return barrioRepository.buscarBarrioPorId(idBarrio);
    }
}
