
package com.crossbox.fitnessclub.entity;

import com.crossbox.fitnessclub.dto.ActividadDTO;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;


@Component
public class ActividadMapper {
    
     private static final DateTimeFormatter DIA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     private static final DateTimeFormatter HORARIO_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public ActividadDTO toDTO(Actividad actividad) {
        ActividadDTO actividadDTO = new ActividadDTO();
        actividadDTO.setId(actividad.getId());
        actividadDTO.setNombre(actividad.getNombre());
        actividadDTO.setDescripcion(actividad.getDescripcion());
        actividadDTO.setDia(actividad.getDia());
        actividadDTO.setHorario(actividad.getHorario());
        actividadDTO.setCupo(actividad.getCupo());
        return actividadDTO;
    }
    
}
