
package com.crossbox.fitnessclub.security.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String dni;
    
    @NotNull
    private String direccion;
    
    @NotNull
    private String localidad;
    
    @NotNull
    private String telefono;
    //ver si este tipo de configuracion no trae problemas, se cambio la propiedad lenght por columndefinition para aumentar el tamaño de la columna
    @Column(name = "fotoPerfil", nullable = false, columnDefinition = "LONGTEXT")
    private String fotoPerfil;
    
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn (name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    private int suscripcionActual;
    
    private String fechaActualSus;
    
    private int clasesTomadas;
    
    private int clasesRestantes;
    
    private String idImagenCloudinary;
    
    
        
    //generar constructores

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, String direccion, String localidad, String telefono, String fotoPerfil, String nombreUsuario, String email, String password, int suscripcionActual, String fechaActualSus, int clasesTomadas, int clasesRestantes, String idImagenCloudinary) {
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
        this.clasesRestantes = clasesRestantes;
        this.idImagenCloudinary = idImagenCloudinary;
    }

    

                
    
}
