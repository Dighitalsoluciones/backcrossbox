
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
                dtodisciplinas.getNombre(), dtodisciplinas.getDescripcion(), dtodisciplinas.getImagen(),dtodisciplinas.getDia1(),dtodisciplinas.getDia2(),
        dtodisciplinas.getDia3(),dtodisciplinas.getDia4(),dtodisciplinas.getDia5(),dtodisciplinas.getDia6(),dtodisciplinas.getDia7(),dtodisciplinas.getHs1(),
        dtodisciplinas.getHs2(), dtodisciplinas.getHs3(),dtodisciplinas.getHs4(),dtodisciplinas.getHs5(),dtodisciplinas.getHs6(),dtodisciplinas.getHs7(),
        dtodisciplinas.getHs8(), dtodisciplinas.getHs9(),dtodisciplinas.getHs10(),dtodisciplinas.getHs11(),dtodisciplinas.getHs12(),dtodisciplinas.getHs13(),
        dtodisciplinas.getHs14(),dtodisciplinas.getHs15(),dtodisciplinas.getHs16(),dtodisciplinas.getHs17(),dtodisciplinas.getHs18(),dtodisciplinas.getCupohs1(),
        dtodisciplinas.getCupohs2(),dtodisciplinas.getCupohs3(),dtodisciplinas.getCupohs4(),dtodisciplinas.getCupohs5(),dtodisciplinas.getCupohs6(),dtodisciplinas.getCupohs7(),
        dtodisciplinas.getCupohs8(),dtodisciplinas.getCupohs9(),dtodisciplinas.getCupohs10(),dtodisciplinas.getCupohs11(),dtodisciplinas.getCupohs12(),dtodisciplinas.getCupohs13(),
        dtodisciplinas.getCupohs14(),dtodisciplinas.getCupohs15(),dtodisciplinas.getCupohs16(),dtodisciplinas.getCupohs17(),dtodisciplinas.getCupohs18());
        sDisciplinas.save(disciplinas);
        return new ResponseEntity(new Mensaje("Nuevo objeto creado exitosamente"), HttpStatus.OK);
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
        disciplinas.setDia1(dtodisciplinas.getDia1());
        disciplinas.setDia2(dtodisciplinas.getDia2());
        disciplinas.setDia3(dtodisciplinas.getDia3());
        disciplinas.setDia4(dtodisciplinas.getDia4());
        disciplinas.setDia5(dtodisciplinas.getDia5());
        disciplinas.setDia6(dtodisciplinas.getDia6());
        disciplinas.setDia7(dtodisciplinas.getDia7());
        disciplinas.setHs1(dtodisciplinas.getHs1());
        disciplinas.setHs2(dtodisciplinas.getHs2());
        disciplinas.setHs3(dtodisciplinas.getHs3());
        disciplinas.setHs4(dtodisciplinas.getHs4());
        disciplinas.setHs5(dtodisciplinas.getHs5());
        disciplinas.setHs6(dtodisciplinas.getHs6());
        disciplinas.setHs7(dtodisciplinas.getHs7());
        disciplinas.setHs8(dtodisciplinas.getHs8());
        disciplinas.setHs9(dtodisciplinas.getHs9());
        disciplinas.setHs10(dtodisciplinas.getHs10());
        disciplinas.setHs11(dtodisciplinas.getHs11());
        disciplinas.setHs12(dtodisciplinas.getHs12());
        disciplinas.setHs13(dtodisciplinas.getHs13());
        disciplinas.setHs14(dtodisciplinas.getHs14());
        disciplinas.setHs15(dtodisciplinas.getHs15());
        disciplinas.setHs16(dtodisciplinas.getHs16());
        disciplinas.setHs17(dtodisciplinas.getHs17());
        disciplinas.setHs18(dtodisciplinas.getHs18());
        disciplinas.setCupohs1(dtodisciplinas.getCupohs1());
        disciplinas.setCupohs2(dtodisciplinas.getCupohs2());
        disciplinas.setCupohs3(dtodisciplinas.getCupohs3());
        disciplinas.setCupohs4(dtodisciplinas.getCupohs4());
        disciplinas.setCupohs5(dtodisciplinas.getCupohs5());
        disciplinas.setCupohs6(dtodisciplinas.getCupohs6());
        disciplinas.setCupohs7(dtodisciplinas.getCupohs7());
        disciplinas.setCupohs8(dtodisciplinas.getCupohs8());
        disciplinas.setCupohs9(dtodisciplinas.getCupohs9());
        disciplinas.setCupohs10(dtodisciplinas.getCupohs10());
        disciplinas.setCupohs11(dtodisciplinas.getCupohs11());
        disciplinas.setCupohs12(dtodisciplinas.getCupohs12());
        disciplinas.setCupohs13(dtodisciplinas.getCupohs13());
        disciplinas.setCupohs14(dtodisciplinas.getCupohs14());
        disciplinas.setCupohs15(dtodisciplinas.getCupohs15());
        disciplinas.setCupohs16(dtodisciplinas.getCupohs16());
        disciplinas.setCupohs17(dtodisciplinas.getCupohs17());
        disciplinas.setCupohs18(dtodisciplinas.getCupohs18());
        
        
        
               
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
