
package com.crossbox.fitnessclub.service;

import com.crossbox.fitnessclub.entity.Actividad;
import com.crossbox.fitnessclub.repository.ActividadRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ActividadService {
  @Autowired
  private ActividadRepository actividadRepository;

  public List<Actividad> getActividades(LocalDate fecha) {
    return actividadRepository.findByDia(fecha);
  }

  public Optional<Actividad> buscarActividad(Long id) {
    return actividadRepository.findById(id);
  }

  public Actividad guardarActividad(Actividad actividad) {
    return actividadRepository.save(actividad);
  }

  public void eliminarActividad(Long id) {
    actividadRepository.deleteById(id);
  }
  
   public List<Actividad> list(){
        return actividadRepository.findAll();
    }
}
