
package com.crossbox.fitnessclub.controller;

import com.crossbox.fitnessclub.dto.dtoFecha;
import com.crossbox.fitnessclub.service.ServFecha;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fechas")
@CrossOrigin(origins = "https://localhost4200")
public class FechaController {
    @Autowired()
    ServFecha servfecha;

    

    @GetMapping
    public ResponseEntity<List<dtoFecha>> obtenerFechas(@RequestParam("fechaInicio") Date fechaInicio,
                                                         @RequestParam("fechaFin") Date fechaFin) {
        List<dtoFecha> fechas = servfecha.obtenerFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(fechas);
    }
}