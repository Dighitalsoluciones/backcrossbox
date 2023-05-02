
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
  @Column(name = "fotoPerfil", columnDefinition = "MEDIUMTEXT")
  private String fotoPerfil;
  private String nombreUsuario; 
  
  //constructor

    public TurnoDTO() {
    }

    public TurnoDTO(String actividad, String dia, String horario, String nombre, String apellido, String dni, String telefono, String fotoPerfil, String nombreUsuario) {
        this.actividad = actividad;
        this.dia = dia;
        this.horario = horario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.fotoPerfil = fotoPerfil;
        this.nombreUsuario = nombreUsuario;
    }
  
}
