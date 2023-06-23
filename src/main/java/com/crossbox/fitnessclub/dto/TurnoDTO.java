
package com.crossbox.fitnessclub.dto;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnoDTO {
  private Long id;
  private String actividad;
  private String dia;
  private String horario;
  //Respecto al usuario
  private String nombre;
  private String apellido;
  private String dni;
  private String telefono;
  private String nombreUsuario; 
  private Long id_actividad;
  
  //constructor

    public TurnoDTO() {
    }

    public TurnoDTO(String actividad, String dia, String horario, String nombre, String apellido, String dni, String telefono, String nombreUsuario, Long id_actividad) {
        this.actividad = actividad;
        this.dia = dia;
        this.horario = horario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.id_actividad = id_actividad;
    }

    
  
}
