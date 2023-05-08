
package com.crossbox.fitnessclub.controller;

import com.crossbox.fitnessclub.dto.TurnoDTO;
import com.crossbox.fitnessclub.entity.Turno;
import com.crossbox.fitnessclub.security.controller.Mensaje;
import com.crossbox.fitnessclub.service.TurnoService;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "http://localhost:4200")
public class TurnoController {
    
  @Autowired
  TurnoService turnoService;
  
  @GetMapping("/{id}")
  public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) {
    Optional<Turno> turno = turnoService.buscarTurnoPorId(id);

    if (turno.isPresent()) {
      return ResponseEntity.ok(turno.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  
   @GetMapping("/lista")
    public ResponseEntity<List<Turno>> list() {
        List<Turno> list = turnoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Turno> geyById(@PathVariable("id") Long id){
        if(!turnoService.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        Turno turno = turnoService.buscarTurnoPorId(id).get();
        return new ResponseEntity(turno, HttpStatus.OK);
    }

  @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TurnoDTO turnoDto) {
        if (StringUtils.isBlank(turnoDto.getActividad())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(turnoDto.getDia())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }  
        
        if (StringUtils.isBlank(turnoDto.getHorario())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Turno turno = new Turno(
            turnoDto.getActividad(), turnoDto.getDia(), turnoDto.getHorario(),turnoDto.getNombre(),turnoDto.getApellido(),
            turnoDto.getDni(),turnoDto.getTelefono(),turnoDto.getNombreUsuario());
        turnoService.save(turno);
        return new ResponseEntity(new Mensaje("Nuevo turno creado exitosamente"), HttpStatus.OK);
    }

  @PutMapping("/update/{id}")
  public ResponseEntity<Turno> actualizarTurno(
          @PathVariable Long id, @RequestBody Turno turno) {
    Optional<Turno> turnoExistente = turnoService.buscarTurnoPorId(id);

    if (turnoExistente.isPresent()) {
      turno.setId(id);
      return ResponseEntity.ok(turnoService.save(turno));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /*Metodo anterior
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
    Optional<Turno> turno = turnoService.buscarTurnoPorId(id);

    if (turno.isPresent()) {
      turnoService.eliminarTurno(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }*/
  
  @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!turnoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Id Inexistente"), HttpStatus.NOT_FOUND);
        }
        turnoService.eliminarTurno(id);
        return new ResponseEntity(new Mensaje("Objeto eliminado correctamente"), HttpStatus.OK);
    }

}
