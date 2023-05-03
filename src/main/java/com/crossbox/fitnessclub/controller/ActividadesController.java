
package com.crossbox.fitnessclub.controller;

import com.crossbox.fitnessclub.dto.ActividadDTO;
import com.crossbox.fitnessclub.entity.Actividad;
import com.crossbox.fitnessclub.entity.ActividadMapper;
import com.crossbox.fitnessclub.repository.ActividadRepository;
import com.crossbox.fitnessclub.security.controller.Mensaje;
import com.crossbox.fitnessclub.service.ActividadService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/actividades")
@CrossOrigin(origins = "http://localhost:4200")
public class ActividadesController {
  @Autowired
  private ActividadRepository actividadRepository;
  
  @Autowired()
    ActividadService actividadService;

    @Autowired
    private ActividadMapper actividadMapper;
    
     @GetMapping("/lista")
    public ResponseEntity<List<Actividad>> list() {
        List<Actividad> list = actividadService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public List<ActividadDTO> getActividades(
            @RequestParam(name = "fecha") String fecha) {
        List<Actividad> actividades = actividadRepository.findByDia(fecha);
        return actividades.stream().map(actividadMapper::toDTO).collect(Collectors.toList());
    }


  @PostMapping("/reservar/{id}")
  public ResponseEntity<?> reservarActividad(@PathVariable("id") Long id) {
    Optional<Actividad> optionalActividad = actividadRepository.findById(id);
    if (optionalActividad.isPresent()) {
      Actividad actividad = optionalActividad.get();
      if (actividad.getCupo() > 0) {
        actividad.setCupo(actividad.getCupo() - 1);
        actividadRepository.save(actividad);
        return ResponseEntity.ok().build();
      } else {
        return ResponseEntity.badRequest().body("No hay cupo disponible");
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }
@PostMapping("/crear")
  public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad actividad) {
    Actividad nuevaActividad = actividadService.guardarActividad(actividad);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(nuevaActividad.getId()).toUri())
            .body(nuevaActividad);
  }
  
  @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ActividadDTO actDTO) {
        if (StringUtils.isBlank(actDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(actDTO.getDia())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }  
        
        if (StringUtils.isBlank(actDTO.getHorario())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Actividad actividad = new Actividad(
                actDTO.getNombre(), actDTO.getDescripcion(), actDTO.getDia(),actDTO.getHorario(),actDTO.getCupo());
        actividadService.save(actividad);
        return new ResponseEntity(new Mensaje("Nuevo turno creado exitosamente"), HttpStatus.OK);
    }
  

  @PutMapping("/{id}")
  public ResponseEntity<Actividad> actualizarActividad(
          @PathVariable Long id, @RequestBody Actividad actividad) {
    Optional<Actividad> actividadExistente = actividadService.buscarActividad(id);

    if (actividadExistente.isPresent()) {
      actividad.setId(id);
      return ResponseEntity.ok(actividadService.guardarActividad(actividad));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarActividad(@PathVariable Long id) {
    Optional<Actividad> actividad = actividadService.buscarActividad(id);

    if (actividad.isPresent()) {
      actividadService.eliminarActividad(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

   @GetMapping("/detail/{id}")
  public ResponseEntity<Actividad> buscarActividad(@PathVariable Long id) {
    Optional<Actividad> actividad = actividadService.buscarActividad(id);

    if (actividad.isPresent()) {
      return ResponseEntity.ok(actividad.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  
 
}