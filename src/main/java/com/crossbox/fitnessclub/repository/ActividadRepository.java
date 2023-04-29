
package com.crossbox.fitnessclub.repository;

import com.crossbox.fitnessclub.entity.Actividad;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
  List<Actividad> findByDia(String dia);
}