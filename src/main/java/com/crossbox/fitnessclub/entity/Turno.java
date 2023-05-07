
package com.crossbox.fitnessclub.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
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
  @Column(nullable = false)
  private String actividad;
  @Column(nullable = false)
  private String dia;
  @Column(nullable = false)
  private String horario;

  //Respecto al usuario
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String apellido;
  @Column(nullable = false)
  private String dni;
  @Column(nullable = false)
  private String telefono;
  @Column(nullable = false)
  private String nombreUsuario;
  
  //constructor

    public Turno() {
    }

    public Turno(String actividad, String dia, String horario, String nombre, String apellido, String dni, String telefono, String nombreUsuario) {
        this.actividad = actividad;
        this.dia = dia;
        this.horario = horario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;        
        this.nombreUsuario = nombreUsuario;
    }

   
  

}