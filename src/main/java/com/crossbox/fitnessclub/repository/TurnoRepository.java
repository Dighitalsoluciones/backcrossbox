
package com.crossbox.fitnessclub.repository;

import com.crossbox.fitnessclub.entity.Turno;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByActividadIdAndHoraInicioGreaterThanEqualAndHoraFinLessThanEqual(
    Long ActividadId, LocalDateTime horarioInicio, LocalDateTime horarioFin);
}
