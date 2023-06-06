
package com.crossbox.fitnessclub.security.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
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
    
    
}
