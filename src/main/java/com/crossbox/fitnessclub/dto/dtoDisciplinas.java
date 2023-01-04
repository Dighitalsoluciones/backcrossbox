
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
    
    private String dia1;
    private String dia2;
    private String dia3;
    private String dia4;
    private String dia5;
    private String dia6;
    private String dia7;
    
    private String hs1;
    private String hs2;
    private String hs3;
    private String hs4;
    private String hs5;
    private String hs6;
    private String hs7;
    private String hs8;
    private String hs9;
    private String hs10;
    private String hs11;
    private String hs12;
    private String hs13;
    private String hs14;
    private String hs15;
    private String hs16;
    private String hs17;
    private String hs18;
    
    private String cupohs1;
    private String cupohs2;
    private String cupohs3;
    private String cupohs4;
    private String cupohs5;
    private String cupohs6;
    private String cupohs7;
    private String cupohs8;
    private String cupohs9;
    private String cupohs10;
    private String cupohs11;
    private String cupohs12;
    private String cupohs13;
    private String cupohs14;
    private String cupohs15;
    private String cupohs16;
    private String cupohs17;
    private String cupohs18;
    
    //constructor

    public dtoDisciplinas() {
    }

    public dtoDisciplinas(String nombre, String descripcion, String imagen, String dia1, String dia2, String dia3, String dia4, String dia5, String dia6, String dia7,
            String hs1, String hs2, String hs3, String hs4, String hs5, String hs6, String hs7, String hs8, String hs9, String hs10, String hs11, String hs12, String hs13,
            String hs14, String hs15, String hs16, String hs17, String hs18, String cupohs1, String cupohs2, String cupohs3, String cupohs4, String cupohs5, String cupohs6,
            String cupohs7, String cupohs8, String cupohs9, String cupohs10, String cupohs11, String cupohs12, String cupohs13, String cupohs14, String cupohs15,
            String cupohs16, String cupohs17, String cupohs18) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.dia3 = dia3;
        this.dia4 = dia4;
        this.dia5 = dia5;
        this.dia6 = dia6;
        this.dia7 = dia7;
        this.hs1 = hs1;
        this.hs2 = hs2;
        this.hs3 = hs3;
        this.hs4 = hs4;
        this.hs5 = hs5;
        this.hs6 = hs6;
        this.hs7 = hs7;
        this.hs8 = hs8;
        this.hs9 = hs9;
        this.hs10 = hs10;
        this.hs11 = hs11;
        this.hs12 = hs12;
        this.hs13 = hs13;
        this.hs14 = hs14;
        this.hs15 = hs15;
        this.hs16 = hs16;
        this.hs17 = hs17;
        this.hs18 = hs18;
        this.cupohs1 = cupohs1;
        this.cupohs2 = cupohs2;
        this.cupohs3 = cupohs3;
        this.cupohs4 = cupohs4;
        this.cupohs5 = cupohs5;
        this.cupohs6 = cupohs6;
        this.cupohs7 = cupohs7;
        this.cupohs8 = cupohs8;
        this.cupohs9 = cupohs9;
        this.cupohs10 = cupohs10;
        this.cupohs11 = cupohs11;
        this.cupohs12 = cupohs12;
        this.cupohs13 = cupohs13;
        this.cupohs14 = cupohs14;
        this.cupohs15 = cupohs15;
        this.cupohs16 = cupohs16;
        this.cupohs17 = cupohs17;
        this.cupohs18 = cupohs18;
    }
    
}
