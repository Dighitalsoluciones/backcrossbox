
package com.crossbox.fitnessclub.security.repository;

import com.crossbox.fitnessclub.security.entity.Rol;
import com.crossbox.fitnessclub.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

