
package com.crossbox.fitnessclub.security.service;

import com.crossbox.fitnessclub.security.entity.Usuario;
import com.crossbox.fitnessclub.security.repository.iUsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioService {
    @Autowired
    iUsuarioRepository iusuarioRepository;
    
     
    
    public boolean existsByEmail(String email){
        return iusuarioRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        iusuarioRepository.save(usuario);
    }
    public Optional<Usuario> getOne(int id){
        return iusuarioRepository.findById(id);
    }
    //probando
     public Optional<Usuario> getOneOptional(String nombreUsuario){
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
     public boolean existsById(int id){
        return iusuarioRepository.existsById(id);
    }
     
    //estos son los dos agregados
    public List<Usuario> list(){
        return iusuarioRepository.findAll();
    }
    
     public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }
     
      public boolean existsByNombreUsuario(String nombreUsuario){
        return iusuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
      public void delete(int id){
        iusuarioRepository.deleteById(id);
    }
   
}