
package com.crossbox.fitnessclub.service;

import com.crossbox.fitnessclub.entity.Imagen;
import com.crossbox.fitnessclub.repository.ImagenRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImagenService {
    
    @Autowired
    ImagenRepository imagenRepository;
    
    public List<Imagen> list(){
        return imagenRepository.findByOrderById();
    }
    
    public void save(Imagen imagen){
        imagenRepository.save(imagen);
    }
    
    public void delete(int id){
        imagenRepository.deleteById(id);
    }
    
    public Optional<Imagen> getOne(int id){
        return imagenRepository.findById(id);
    }
    
    public Boolean exists(int id){
        return imagenRepository.existsById(id);
    }
}
