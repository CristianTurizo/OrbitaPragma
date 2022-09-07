package com.pragma.orbita.driver.users.application.DTOConsulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoConsulta {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String documento;
    private String tipoDocumento;
    private String email;
    private String telefono;
    private List<Integer> roles;
}
