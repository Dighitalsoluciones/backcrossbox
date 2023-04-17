
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

  public List<Turno> buscarTurnos(Long actividadId, LocalDate fecha) {
    LocalDateTime horaInicio = fecha.atStartOfDay();
    LocalDateTime horaFin = horaInicio.plusHours(24);

    return turnoRepository.findByActividadIdAndHoraInicioGreaterThanEqualAndHoraFinLessThanEqual(
        actividadId, horaInicio, horaFin);
  }

  public Optional<Turno> buscarTurno(Long id) {
    return turnoRepository.findById(id);
  }

  public Turno guardarTurno(Turno turno) {
    return turnoRepository.save(turno);
  }

  public void eliminarTurno(Long id) {
    turnoRepository.deleteById(id);
  }
}
