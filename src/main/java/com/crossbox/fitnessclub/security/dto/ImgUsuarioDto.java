
package com.crossbox.fitnessclub.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgUsuarioDto {
    
    private String fotoPerfil;
    private String idImagenCloudinary;
    private int idImagen;

    public ImgUsuarioDto() {
    }

    public ImgUsuarioDto(String fotoPerfil, String idImagenCloudinary, int idImagen) {
        this.fotoPerfil = fotoPerfil;
        this.idImagenCloudinary = idImagenCloudinary;
        this.idImagen = idImagen;
    }
    
    
}
