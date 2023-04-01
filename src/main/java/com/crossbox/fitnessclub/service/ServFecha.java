
package com.crossbox.fitnessclub.service;

import com.crossbox.fitnessclub.dto.dtoFecha;
import com.crossbox.fitnessclub.entity.Fecha;
import com.crossbox.fitnessclub.repository.FechaRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServFecha {
    @Autowired
    FechaRepository fechaRepository;

    public List<dtoFecha> obtenerFechas(Date fechaInicio, Date fechaFin) {
        List<Fecha> fechas = fechaRepository.findByFechaBetween(fechaInicio, fechaFin);
        return fechas.stream()
                .map(fecha -> new dtoFecha(fecha.getId(), fecha.getFecha()))
                .collect(Collectors.toList());
    }
}
