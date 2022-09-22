package com.pragma.orbita.driver.routes.application.service;

import com.pragma.orbita.driver.routes.application.dto.BarrioDto;
import com.pragma.orbita.driver.routes.application.mapper.IBarrioMapper;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.routes.domain.model.Barrio;
import com.pragma.orbita.driver.routes.domain.usecase.BarrioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarrioService {

    private final IBarrioMapper barrioMapper;
    private final BarrioUseCase barrioUseCase;

    public ObjetoRespuesta<BarrioDto> guardarBarrio(BarrioDto barrioDto) {
        Barrio nuevoBarrio = barrioUseCase.guardarBarrio(
                barrioMapper.toDomain(barrioDto));

        return nuevoBarrio == null
                ? new ObjetoRespuesta<>(null, "No se puedo guardar el barrio")
                : new ObjetoRespuesta<>(
                barrioMapper.toDto(nuevoBarrio),
                "Barrio guardado con exito");
    }

    public ObjetoRespuesta<BarrioDto> buscarBarrioPorNombre(String nombreBarrio) {
        Barrio barrio = barrioUseCase.buscarBarrioPorNombre(nombreBarrio);

        return barrio == null
                ? new ObjetoRespuesta<>(null, "No se encotraron registros")
                : new ObjetoRespuesta<>(
                barrioMapper.toDto(barrio),
                "Barrio encontrado con exito");
    }
}
