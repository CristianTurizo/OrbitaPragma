package com.pragma.orbita.driver.routes.infraestructure.feignclient;

import com.pragma.orbita.driver.routes.application.dto.UsuarioDto;
import com.pragma.orbita.driver.routes.application.respuesta.ObjetoRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient(name = "users", url = "${feign.microservice.users}")
public interface IUsuarioFeing {

    @GetMapping("/{idUsuario}")
    ResponseEntity<ObjetoRespuesta<UsuarioDto>> buscarUsuarioPorId(@NotNull @PathVariable int idUsuario);
}
