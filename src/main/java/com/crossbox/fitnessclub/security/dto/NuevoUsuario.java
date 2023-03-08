
package com.crossbox.fitnessclub.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String dni;
    private String direccion;
    private String localidad;
    private String telefono;
    private String fotoPerfil;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
    private int suscripcionActual;
    private String fechaActualSus;
    private int clasesTomadas;
    private int ClasesRestantes;
    
    //constructor
    
    
       
}
