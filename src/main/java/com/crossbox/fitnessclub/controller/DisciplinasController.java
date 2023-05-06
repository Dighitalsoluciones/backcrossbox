
package com.crossbox.fitnessclub.controller;

import com.crossbox.fitnessclub.dto.dtoDisciplinas;
import com.crossbox.fitnessclub.entity.Disciplinas;
import com.crossbox.fitnessclub.security.controller.Mensaje;
import com.crossbox.fitnessclub.service.ServDisciplinas;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
@CrossOrigin(origins = "http://localhost:4200")
public class DisciplinasController {
    
    @Autowired()
    ServDisciplinas sDisciplinas;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Disciplinas>> list() {
        List<Disciplinas> list = sDisciplinas.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Disciplinas> geyById(@PathVariable("id") int id){
        if(!sDisciplinas.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        Disciplinas disciplinas = sDisciplinas.getOne(id).get();
        return new ResponseEntity(disciplinas, HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sDisciplinas.existsById(id)) {
            return new ResponseEntity(new Mensaje("Id Inexistente"), HttpStatus.NOT_FOUND);
        }
        sDisciplinas.delete(id);
        return new ResponseEntity(new Mensaje("Objeto eliminado correctamente"), HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoDisciplinas dtodisciplinas) {
        if (StringUtils.isBlank(dtodisciplinas.getNombre())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtodisciplinas.getDescripcion())) {
            return new ResponseEntity(new Mensaje("Campo Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
           
        if (sDisciplinas.existByNombre(dtodisciplinas.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }

        Disciplinas disciplinas = new Disciplinas(
                dtodisciplinas.getNombre(), dtodisciplinas.getDescripcion(), dtodisciplinas.getImagen(), dtodisciplinas.getProfesor());
        sDisciplinas.save(disciplinas);
        return new ResponseEntity(new Mensaje("Nueva disciplina creada exitosamente"), HttpStatus.OK);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoDisciplinas dtodisciplinas){
        if(!sDisciplinas.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.NOT_FOUND);
        }
        if(sDisciplinas.existByNombre(dtodisciplinas.getNombre()) && sDisciplinas.
                getByNombre(dtodisciplinas.getNombre()).get().getId() != id){
        return new ResponseEntity(new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
    }
        if(StringUtils.isBlank(dtodisciplinas.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtodisciplinas.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
       
        
        
        
        
        Disciplinas disciplinas = sDisciplinas.getOne(id).get();
        
        disciplinas.setNombre(dtodisciplinas.getNombre());
        disciplinas.setDescripcion(dtodisciplinas.getDescripcion());
        disciplinas.setImagen(dtodisciplinas.getImagen());
        disciplinas.setProfesor(dtodisciplinas.getProfesor());
        sDisciplinas.save(disciplinas);
        
        return new ResponseEntity(new Mensaje("Objeto actualizado correctamente"), HttpStatus.OK);
    }
    
    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Disciplinas> getByNombreUsuario(@PathVariable("nombre") String nombre){
        if(!sDisciplinas.existByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Disciplinas disciplinas = sDisciplinas.getByNombre(nombre).get();
        return new ResponseEntity(disciplinas, HttpStatus.OK);
    }
}
