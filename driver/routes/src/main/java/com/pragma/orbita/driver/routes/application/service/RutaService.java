package com.pragma.orbita.driver.routes.application.service;

import com.pragma.orbita.driver.routes.application.dto.RutaBarrioDto;
import com.pragma.orbita.driver.routes.application.dto.RutaDto;
import com.pragma.orbita.driver.routes.application.dto.UsuarioDto;
import com.pragma.orbita.driver.routes.application.mapper.IRutaBarrioMapper;
import com.pragma.orbita.driver.routes.application.mapper.IRutaMapper;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.routes.domain.model.Ruta;
import com.pragma.orbita.driver.routes.domain.model.RutaBarrio;
import com.pragma.orbita.driver.routes.domain.usecase.RutaBarrioUseCase;
import com.pragma.orbita.driver.routes.domain.usecase.RutaUseCase;
import com.pragma.orbita.driver.routes.infraestructure.feignclient.IUsuarioFeing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RutaService {

    private final IRutaMapper rutaMapper;
    private final IRutaBarrioMapper rutaBarrioMapper;
    private final RutaUseCase rutaUseCase;
    private final RutaBarrioUseCase rutaBarrioUseCase;
    private final IUsuarioFeing usuarioFeing;

    @Transactional
    public ObjetoRespuesta<RutaDto> guardarRuta(RutaDto rutaDto) {
        ResponseEntity<ObjetoRespuesta<UsuarioDto>> usuarioRespuesta = usuarioFeing.buscarUsuarioPorId(rutaDto.getIdUsuario());
        if (!usuarioRespuesta.getStatusCode().is2xxSuccessful()) {
            return new ObjetoRespuesta<>(null, "El usuario al que desea asociar la ruta no existe");
        }

        Ruta nuevaRuta = rutaUseCase.guardarRuta(
                rutaMapper.toDomain(rutaDto));

        List<RutaBarrio> rutaBarrioList = guardarRelacion(rutaDto.getRutaBarrioList(), nuevaRuta.getIdRuta());

        return nuevaRuta == null
                ? new ObjetoRespuesta<>(null, "No se puedo guardar el ruta")
                : new ObjetoRespuesta<>(
                crearRutaDtoRespuesta(nuevaRuta, rutaBarrioList),
                "Ruta guardada con exito");
    }

    public ObjetoRespuesta<RutaDto> buscarRutaPorId(int idRuta) {
        Ruta ruta = rutaUseCase.buscarPorIdRuta(idRuta);
        if (ruta == null) {
            return new ObjetoRespuesta<>(null, "No se encotraron registros");
        }
        List<RutaBarrio> rutaBarrioList = rutaBarrioUseCase.buscarPorIdRuta(ruta.getIdRuta());

        return new ObjetoRespuesta<>(
                crearRutaDtoRespuesta(ruta, rutaBarrioList),
                "Ruta encontrada con exito");
    }

    public ObjetoRespuesta<List<RutaDto>> buscarRutasPorIdUsuario(int idUsuario) {
        List<Ruta> rutaList = rutaUseCase.buscarPorIdUsuario(idUsuario);
        if (rutaList.isEmpty()) {
            return new ObjetoRespuesta<>(null, "No se encotraron registros");
        }

        List<RutaDto> rutaDtoRespuestaList = rutaList.stream()
                .map(ruta -> crearRutaDtoRespuesta(
                                ruta,
                                rutaBarrioUseCase.buscarPorIdRuta(ruta.getIdRuta())
                        )
                )
                .collect(Collectors.toList());

        return new ObjetoRespuesta<>(
                rutaDtoRespuestaList,
                "Ruta encontrada con exito");
    }

    private List<RutaBarrio> guardarRelacion(List<RutaBarrioDto> rutaBarrioList, int idRuta) {
        rutaBarrioList.forEach(rutaBarrioDto ->
                rutaBarrioDto.setIdRuta(idRuta));

        return rutaBarrioList.stream()
                .map(rutaBarrioMapper::toDomain)
                .map(rutaBarrioUseCase::guardarRelacion)
                .collect(Collectors.toList());
    }

    private RutaDto crearRutaDtoRespuesta(Ruta ruta, List<RutaBarrio> rutaBarrioList) {
        RutaDto rutaCreada = rutaMapper.toDto(ruta);
        List<RutaBarrioDto> rutaBarrioDtoList = rutaBarrioMapper.toDtoList(rutaBarrioList);

        rutaCreada.setRutaBarrioList(rutaBarrioDtoList);

        return rutaCreada;
    }

}
