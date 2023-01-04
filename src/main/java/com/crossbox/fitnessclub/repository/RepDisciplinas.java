
package com.crossbox.fitnessclub.repository;

import com.crossbox.fitnessclub.entity.Disciplinas;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepDisciplinas extends JpaRepository<Disciplinas, Integer>{
    public Optional<Disciplinas> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
