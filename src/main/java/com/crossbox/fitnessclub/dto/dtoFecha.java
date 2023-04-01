
package com.crossbox.fitnessclub.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoFecha {
    private Long id;
    @NotBlank
    private Date fecha;

    // constructor

    public dtoFecha() {
    }

    public dtoFecha(Long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    
    
}
