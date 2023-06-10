
package com.crossbox.fitnessclub.security.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {
    
    Cloudinary cloudinary;
    
    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name", "djtra8arp");
        valuesMap.put("api_key", "528881555923299");
        valuesMap.put("api_secret", "YxxLKGLzSpMCaHYVf_xrYaWrXl4");
        cloudinary = new Cloudinary(valuesMap);
    }
    
    public Map upload(MultipartFile multipartFile) throws IOException{
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }
    
    public Map delete(String id) throws IOException{
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }
    
    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File (multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
    
    public Map uploading(String base64Image) throws IOException {
    // Eliminar el prefijo "data:image/jpeg;base64," para obtener solo el contenido base64
    String base64Content = base64Image.substring(base64Image.indexOf(',') + 1);

    byte[] imageBytes = Base64.getDecoder().decode(base64Content.getBytes(StandardCharsets.UTF_8));
    File file = convert(imageBytes);
    Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    file.delete();
    return result;
}

    private File convert(byte[] bytes) throws IOException {
        File file = new File("temp.jpg");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
        return file;
    }
    
    public byte[] cleanAndDecodeBase64(String base64String) {
    String cleanedBase64String = base64String.replaceAll("\\s+", "");
    return Base64Utils.decodeFromString(cleanedBase64String);
}
    
    
}
