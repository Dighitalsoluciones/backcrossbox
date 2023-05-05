
package com.crossbox.fitnessclub.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassUsuarioDto {
    @NotBlank
    private String password;

    public PassUsuarioDto() {
    }

    public PassUsuarioDto(String password) {
        this.password = password;
    }
    
    
}
