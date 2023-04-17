
package com.crossbox.fitnessclub.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "actividad_id")
  private Actividad actividad;

  private LocalDateTime horaInicio;

  private LocalDateTime horaFin;

  private Integer cupoDisponible;
  
  //constructor

    public Turno() {
    }

    public Turno(Actividad actividad, LocalDateTime horaInicio, LocalDateTime horaFin, Integer cupoDisponible) {
        this.actividad = actividad;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupoDisponible = cupoDisponible;
    }
  

}