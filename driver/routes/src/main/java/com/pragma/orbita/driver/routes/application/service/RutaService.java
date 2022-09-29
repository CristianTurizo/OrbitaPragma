package com.pragma.orbita.driver.routes.application.service;

import com.pragma.orbita.driver.routes.application.dto.RutaDto;
import com.pragma.orbita.driver.routes.application.dto.UsuarioDto;
import com.pragma.orbita.driver.routes.application.mapper.IRutaMapper;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.routes.domain.model.Ruta;
import com.pragma.orbita.driver.routes.domain.usecase.RutaUseCase;
import com.pragma.orbita.driver.routes.infraestructure.feignclient.IUsuarioFeing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaService {

    private final IRutaMapper rutaMapper;
    private final RutaUseCase rutaUseCase;
    private final IUsuarioFeing usuarioFeing;

    public ObjetoRespuesta<RutaDto> guardarRuta(RutaDto rutaDto) {
        ResponseEntity<ObjetoRespuesta<UsuarioDto>> usuarioRespuesta = usuarioFeing.buscarUsuarioPorId(rutaDto.getIdUsuario());
        if (!usuarioRespuesta.getStatusCode().is2xxSuccessful()) {
            return new ObjetoRespuesta<>(null, "El usuario al que desea asociar la ruta no existe");
        }

        Ruta nuevoRuta = rutaUseCase.guardarRuta(
                rutaMapper.toDomain(rutaDto));

        return nuevoRuta == null
                ? new ObjetoRespuesta<>(null, "No se puedo guardar el ruta")
                : new ObjetoRespuesta<>(
                rutaMapper.toDto(nuevoRuta),
                "Ruta guardada con exito");
    }

    public ObjetoRespuesta<RutaDto> buscarRutaPorId(int idRuta) {
        Ruta ruta = rutaUseCase.buscarPorIdRuta(idRuta);

        return ruta == null
                ? new ObjetoRespuesta<>(null, "No se encotraron registros")
                : new ObjetoRespuesta<>(
                rutaMapper.toDto(ruta),
                "Ruta encontrada con exito");
    }

    public ObjetoRespuesta<List<RutaDto>> buscarRutasPorIdUsuario(int idUsuario) {
        List<Ruta> rutaList = rutaUseCase.buscarPorIdUsuario(idUsuario);

        return rutaList == null
                ? new ObjetoRespuesta<>(null, "No se encotraron registros")
                : new ObjetoRespuesta<>(
                rutaMapper.toDtoList(rutaList),
                "Ruta encontrada con exito");
    }
}
