package com.ttolivet.usmolivet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileUpload {

    @Autowired
    private String baseDir;

    @Value("${app.uploads.dir}")
    private String uploadDir;

    @Value("${app.uploadPicture.dir}")
    private String pictureDir;

    public String getExtension(String file) {
        return Optional.ofNullable(file)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(file.lastIndexOf("."))).get();
    }

    public String writeFile(MultipartFile file, String dir, String fileName) {
        Path path = null;
        try {
            byte[] bytes = file.getBytes();
            path = Paths.get(baseDir + File.separator + uploadDir + File.separator + pictureDir + File.separator + dir + File.separator + fileName + getExtension(file.getOriginalFilename()));
            Files.write(path, bytes).toFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "/" + uploadDir + "/" + pictureDir + "/" + dir + "/" + fileName + getExtension(file.getOriginalFilename());
    }

    public void deleteFile(String file) {
        try {
            File deleteFile = new File(".." + file);
            deleteFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
