
package com.crossbox.fitnessclub.security.service;

import com.cloudinary.Cloudinary;
import java.io.File;
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
    
    public Map upload(MultipartFile multipartFile){
        return null;
    }
    
    public Map delete(String id){
        return null;
    }
    
 
    
}
