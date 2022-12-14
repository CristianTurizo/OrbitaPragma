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
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barrio")
public class BarrioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBarrio;
    private String nombre;
}
