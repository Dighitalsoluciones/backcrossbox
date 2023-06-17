
package com.crossbox.fitnessclub.security.service;

import com.crossbox.fitnessclub.security.entity.Usuario;
import com.crossbox.fitnessclub.security.repository.iUsuarioRepository;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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
   
      public void updateFoto(int id, InputStream fotoPerfil){
         try (Connection connection = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/bdcrossbox", "root", "")){
             String sql = "UPDATE Usuario SET fotoPerfil = ? WHERE id = ?";
             PreparedStatement statement = 
             connection.prepareStatement(sql);
                     statement.setBlob(1, fotoPerfil);
             statement.setInt(2, id);
             statement.executeUpdate();
         } catch (Exception e){
         }
          
      }
      
      public Optional <Usuario> getByEmail(String email) {
        return iusuarioRepository.findByEmail(email);
}
}