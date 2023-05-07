
package com.crossbox.fitnessclub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Disciplinas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    @Column(name = "imagen", nullable = false, columnDefinition = "LONGTEXT")
    private String imagen;
    private String profesor;
      //constructor

    public Disciplinas() {
    }

    public Disciplinas(String nombre, String descripcion, String imagen, String profesor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.profesor = profesor;
    }
    
    
}
