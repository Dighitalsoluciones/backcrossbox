
package com.crossbox.fitnessclub.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class dtoFecha {
    private Long id;
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    // constructor

    public dtoFecha() {
    }

    public dtoFecha(Long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    
    
}
