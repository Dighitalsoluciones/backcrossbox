
package com.crossbox.fitnessclub.security.controller;


import com.crossbox.fitnessclub.security.dto.JwtDto;
import com.crossbox.fitnessclub.security.dto.LoginUsuario;
import com.crossbox.fitnessclub.security.dto.NuevoUsuario;
import com.crossbox.fitnessclub.security.entity.Rol;
import com.crossbox.fitnessclub.security.entity.Usuario;
import com.crossbox.fitnessclub.security.enums.RolNombre;
import com.crossbox.fitnessclub.security.jwt.JwtProvider;
import com.crossbox.fitnessclub.security.service.RolService;
import com.crossbox.fitnessclub.security.service.UsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    
       @PutMapping("/update/{nombreUsuario}")
    public ResponseEntity<?> update(@PathVariable("nombreUsuario") String nombreUsuario, @RequestBody NuevoUsuario nuevousuario){
        if(!usuarioService.existsByNombreUsuario(nombreUsuario)){
            return new ResponseEntity(new Mensaje("Usuario inexistente"), HttpStatus.NOT_FOUND);      
       }
         if(StringUtils.isBlank(nuevousuario.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
          if(StringUtils.isBlank(nuevousuario.getApellido())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
       if(StringUtils.isBlank(nuevousuario.getDni())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(nuevousuario.getNombreUsuario())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(nuevousuario.getEmail())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(nuevousuario.getPassword())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
                
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
        
        usuario.setNombre(nuevousuario.getNombre());
        usuario.setApellido(nuevousuario.getApellido());
        usuario.setDni(nuevousuario.getDni());
        usuario.setDireccion(nuevousuario.getDireccion());
        usuario.setLocalidad(nuevousuario.getLocalidad());
        usuario.setTelefono(nuevousuario.getTelefono());
        usuario.setFotoPerfil(nuevousuario.getFotoPerfil());
        usuario.setNombreUsuario(nuevousuario.getNombreUsuario());
        usuario.setEmail(nuevousuario.getEmail());
        usuario.setPassword(nuevousuario.getPassword());
       
        usuario.setSuscripcionActual(nuevousuario.getSuscripcionActual());
        usuario.setFechaActualSus(nuevousuario.getFechaActualSus());
        usuario.setClasesTomadas(nuevousuario.getClasesTomadas());
        usuario.setClasesRestantes(nuevousuario.getClasesRestantes());
                     
               
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
   
    
}


