
package com.crossbox.fitnessclub.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actividades")
public class Actividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private String descripcion;

  @Column(nullable = false)
  private LocalDate dia;

  @Column(nullable = false)
  private LocalTime horario;

  @Column(nullable = false)
  private Integer cupo;

  // constructor

    public Actividad() {
    }

    public Actividad(String nombre, String descripcion, LocalDate dia, LocalTime horario, Integer cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.horario = horario;
        this.cupo = cupo;
    }
  
  
}
