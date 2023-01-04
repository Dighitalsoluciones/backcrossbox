
package com.crossbox.fitnessclub.service;

import com.crossbox.fitnessclub.entity.Disciplinas;
import com.crossbox.fitnessclub.repository.RepDisciplinas;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServDisciplinas {
    @Autowired
    RepDisciplinas rDisciplinas;
    
    public List<Disciplinas> list(){
        return rDisciplinas.findAll();
    }
 
    public Optional<Disciplinas> getOne(int id){
        return rDisciplinas.findById(id);
    }
    
    public Optional<Disciplinas> getByNombre(String nombre){
        return rDisciplinas.findByNombre(nombre);
    }
    
    public void save(Disciplinas disciplinas){
        rDisciplinas.save(disciplinas);
    }
    
    public void delete(int id){
        rDisciplinas.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rDisciplinas.existsById(id);
    }
    
    public boolean existByNombre(String nombre){
        return rDisciplinas.existsByNombre(nombre);
    }
    
    public Optional<Disciplinas> findById (int id){
        return rDisciplinas.findById(id);
    }  
    
}
