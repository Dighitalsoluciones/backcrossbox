
package com.crossbox.fitnessclub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActividadDTO {
  private Long id;
  private String nombre;
  private String descripcion;
  private String dia;
  private String horario;
  private Integer cupo;

  // Constructor

    public ActividadDTO() {
    }

    public ActividadDTO(String nombre, String descripcion, String dia, String horario, Integer cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.horario = horario;
        this.cupo = cupo;
    }
  

}