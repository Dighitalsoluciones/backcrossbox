
package com.crossbox.fitnessclub.repository;

import com.crossbox.fitnessclub.entity.Fecha;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FechaRepository extends JpaRepository<Fecha, Long> {
    List<Fecha> findByFechaBetween(Date fechaInicio, Date fechaFin);
}
