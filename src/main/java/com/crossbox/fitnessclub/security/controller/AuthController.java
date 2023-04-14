
package com.crossbox.fitnessclub.security.controller;


import com.crossbox.fitnessclub.security.dto.EditUsuarioDto;
import com.crossbox.fitnessclub.security.dto.JwtDto;
import com.crossbox.fitnessclub.security.dto.LoginUsuario;
import com.crossbox.fitnessclub.security.dto.NuevoUsuario;
import com.crossbox.fitnessclub.security.entity.Rol;
import com.crossbox.fitnessclub.security.entity.Usuario;
import com.crossbox.fitnessclub.security.enums.RolNombre;
import com.crossbox.fitnessclub.security.jwt.JwtProvider;
import com.crossbox.fitnessclub.security.service.RolService;
import com.crossbox.fitnessclub.security.service.UsuarioService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
 
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
       if (bindingResult.hasErrors())
           return new ResponseEntity(new Mensaje("Campos mal ingresados o email invalido"), HttpStatus.BAD_REQUEST);
      
       if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
           return new ResponseEntity(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
       
       if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
           return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
       
       Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getDni(), nuevoUsuario.getDireccion(),nuevoUsuario.getLocalidad(),nuevoUsuario.getTelefono(),
               nuevoUsuario.getFotoPerfil(), nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getSuscripcionActual(),
                       nuevoUsuario.getFechaActualSus(), nuevoUsuario.getClasesTomadas(), nuevoUsuario.getClasesRestantes());
       
       Set<Rol> roles = new HashSet<>();
       roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
       if(nuevoUsuario.getRoles().contains("admin"))
           roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
       usuario.setRoles(roles);
       usuarioService.save(usuario);
       
       return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
    
        
        @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EditUsuarioDto editusuariodto){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.NOT_FOUND);
        }
        if(usuarioService.existsByNombreUsuario(editusuariodto.getNombreUsuario()) && usuarioService.
                getByNombreUsuario(editusuariodto.getNombreUsuario()).get().getId() != id){
        return new ResponseEntity(new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(editusuariodto.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
          if(StringUtils.isBlank(editusuariodto.getApellido())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
       if(StringUtils.isBlank(editusuariodto.getDni())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(editusuariodto.getNombreUsuario())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(editusuariodto.getEmail())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(editusuariodto.getPassword())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
                
        Usuario usuario = usuarioService.getOne(id).get();
        
        usuario.setNombre(editusuariodto.getNombre());
        usuario.setApellido(editusuariodto.getApellido());
        usuario.setDni(editusuariodto.getDni());
        usuario.setDireccion(editusuariodto.getDireccion());
        usuario.setLocalidad(editusuariodto.getLocalidad());
        usuario.setTelefono(editusuariodto.getTelefono());
        usuario.setFotoPerfil(editusuariodto.getFotoPerfil());
        usuario.setNombreUsuario(editusuariodto.getNombreUsuario());
        usuario.setEmail(editusuariodto.getEmail());
        usuario.setPassword(passwordEncoder.encode(editusuariodto.getPassword()));
       
        usuario.setSuscripcionActual(editusuariodto.getSuscripcionActual());
        usuario.setFechaActualSus(editusuariodto.getFechaActualSus());
        usuario.setClasesTomadas(editusuariodto.getClasesTomadas());
        usuario.setClasesRestantes(editusuariodto.getClasesRestantes());
                     
               
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario actualizado correctamente"), HttpStatus.OK);
    }
    
    //agregados recien
     @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
       @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario,HttpStatus.OK);
    }
    
    @GetMapping("/detailname/{nombreUsuario}")
    public ResponseEntity<Usuario> getByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
        if(!usuarioService.existsByNombreUsuario(nombreUsuario))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Id Inexistente"), HttpStatus.NOT_FOUND);
        }
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado correctamente"), HttpStatus.OK);
    }
   
    //para actualizar la foto de perfil
    @PutMapping("/foto/{id}")
    public ResponseEntity<?> foto(@PathVariable("id") int id, @RequestBody EditUsuarioDto editusuariodto){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("Id inexistente"), HttpStatus.NOT_FOUND);
        }
        if(usuarioService.existsByNombreUsuario(editusuariodto.getNombreUsuario()) && usuarioService.
                getByNombreUsuario(editusuariodto.getNombreUsuario()).get().getId() != id){
        return new ResponseEntity(new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }
        
                
        Usuario usuario = usuarioService.getOne(id).get();
        
        
        usuario.setFotoPerfil(editusuariodto.getFotoPerfil());         
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario actualizado correctamente"), HttpStatus.OK);
    }
     @PostMapping("/fotoperfil")
     public ResponseEntity<String> uploadImage(@RequestBody Map<String, String> data) throws IOException {
         String imageData = data.get("imageData");
         String base64Image = imageData.split(",")[1];
         byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
         BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
         //guarda la imagen en la base de datos
         
         return ResponseEntity.ok().body("Imagen subida correctamente");
     }
    
     @PutMapping("/fotoperfil/{id}")
     public ResponseEntity updateFoto(@PathVariable int id, @RequestBody EditUsuarioDto editusuariodto){
         Usuario usuario = usuarioService.getOne(id).get();
        
        
        usuario.setFotoPerfil(editusuariodto.getFotoPerfil());         
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Foto actualizada correctamente"), HttpStatus.OK);
       }
     
}


