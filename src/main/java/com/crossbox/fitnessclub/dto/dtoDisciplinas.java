
package com.crossbox.fitnessclub.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoDisciplinas {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String imagen;
    @NotBlank
    private String profesor;
    
    //constructor

    public dtoDisciplinas() {
    }

    public dtoDisciplinas(String nombre, String descripcion, String imagen, String profesor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.profesor = profesor;
    }
    
}
