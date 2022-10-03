package com.pragma.orbita.driver.routes.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ruta_barrio")
public class RutaBarrioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRutaBarrio;
    private int idRuta;
    private int idBarrio;
    private int indice;
    private String puntoEncuentro;
}
