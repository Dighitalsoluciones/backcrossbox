
package com.crossbox.fitnessclub.security.controller;

import com.crossbox.fitnessclub.entity.Imagen;
import com.crossbox.fitnessclub.security.service.CloudinaryService;
import com.crossbox.fitnessclub.service.ImagenService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "https://crossboxfitnessclub1.web.app")
public class CloudinaryController {
    
    @Autowired
    CloudinaryService cloudinaryService;
    
    @Autowired
    ImagenService imagenService;
    
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("imagen no valida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        
        Imagen imagen = new Imagen((String) result.get("original_filename"), (String) result.get("url"), (String) result.get("public_id"));
        imagenService.save(imagen);
        return new ResponseEntity(new Mensaje("Imagen subida correctamente"), HttpStatus.OK);
    }
      
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id) throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new Mensaje("id inexistente"), HttpStatus.NOT_FOUND);
        Imagen imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new Mensaje("Imagen eliminada correctamente"), HttpStatus.OK);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
            
}
