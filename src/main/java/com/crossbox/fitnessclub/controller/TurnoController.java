
package com.crossbox.fitnessclub.controller;

import com.crossbox.fitnessclub.entity.Turno;
import com.crossbox.fitnessclub.service.TurnoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "https://localhost4200")
public class TurnoController {
  @Autowired
  TurnoService turnoService;
  
  @GetMapping("/{id}")
  public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) {
    Optional<Turno> turno = turnoService.buscarTurno(id);

    if (turno.isPresent()) {
      return ResponseEntity.ok(turno.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
    Turno nuevoTurno = turnoService.guardarTurno(turno);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(nuevoTurno.getId()).toUri())
            .body(nuevoTurno);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Turno> actualizarTurno(
          @PathVariable Long id, @RequestBody Turno turno) {
    Optional<Turno> turnoExistente = turnoService.buscarTurno(id);

    if (turnoExistente.isPresent()) {
      turno.setId(id);
      return ResponseEntity.ok(turnoService.guardarTurno(turno));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
    Optional<Turno> turno = turnoService.buscarTurno(id);

    if (turno.isPresent()) {
      turnoService.eliminarTurno(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/actividad/{idActividad}")
  public ResponseEntity<List<Turno>> buscarTurnosPorActividadYFecha(
      @PathVariable Long idActividad, @RequestParam LocalDate fecha) {
    List<Turno> turnos = turnoService.buscarTurnos(idActividad, fecha);

    if (!turnos.isEmpty()) {
      return ResponseEntity.ok(turnos);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
