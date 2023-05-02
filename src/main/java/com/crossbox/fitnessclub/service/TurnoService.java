
package com.crossbox.fitnessclub.service;

import com.crossbox.fitnessclub.entity.Turno;
import com.crossbox.fitnessclub.repository.TurnoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TurnoService {
  @Autowired
  TurnoRepository turnoRepository;

   public List<Turno> list(){
        return turnoRepository.findAll();
    }
  
  public List<Turno> getTurnoPorDia(String dia) {
    return turnoRepository.findByDia(dia);
  }

  public Optional<Turno> buscarTurnoPorId(Long id) {
    return turnoRepository.findById(id);
  }

  public Turno save(Turno turno) {
    return turnoRepository.save(turno);
  }

  public void eliminarTurno(Long id) {
    turnoRepository.deleteById(id);
  }
  
  public boolean existsById(Long id){
        return turnoRepository.existsById(id);
    }
  
}
