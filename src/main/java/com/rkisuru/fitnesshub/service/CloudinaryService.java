package com.rkisuru.fitnesshub.service;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {

    @Value("${cloudinary.api-key}")
    private String cloudinaryApiKey;
    @Value("${cloudinary.api-secret}")
    private String cloudinaryApiSecret;
    @Value("${cloudinary.cloud-name}")
    private String cloudinaryCloudName;

    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", cloudinaryCloudName);
        valuesMap.put("api_key", cloudinaryApiKey);
        valuesMap.put("api_secret", cloudinaryApiSecret);
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map uploadImage(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete file "+ file.getAbsolutePath());
        }
        return result;
    }

    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
