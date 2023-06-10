
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
    
    private String idImagenCloudinary;
    
    //constructor

    public NuevoUsuario() {
    }

    public NuevoUsuario(String nombre, String apellido, String dni, String direccion, String localidad, String telefono, String fotoPerfil, String nombreUsuario, String email, String password, int suscripcionActual, String fechaActualSus, int clasesTomadas, int ClasesRestantes, String idImagenCloudinary) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.fotoPerfil = fotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.suscripcionActual = suscripcionActual;
        this.fechaActualSus = fechaActualSus;
        this.clasesTomadas = clasesTomadas;
        this.ClasesRestantes = ClasesRestantes;
        this.idImagenCloudinary = idImagenCloudinary;
    }

    
    
    
       
}
