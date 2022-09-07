package com.pragma.orbita.driver.users.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_rol")
public class UsuarioRolEntity {

    @Id
    @Column(name = "id_usuario_rol", nullable = false)
    private int idRolUsuario;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @NotNull
    @Column(name = "id_rol", nullable = false)
    private int idRol;

}
