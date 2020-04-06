package com.ttolivet.usmolivet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private String baseDir;

    @Value("${app.uploads.dir}")
    private String uploadDir;

    @Value("${app.uploadPicture.dir}")
    private String pictureDir;

    @Value("${app.pictures.dirs}")
    private String pictureDirs;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        File uploadsDir = new File(baseDir + File.separator + uploadDir);
        File uploadsPicturesDir = new File(uploadsDir, pictureDir);
        if (!uploadsDir.exists()) {
            uploadsPicturesDir.mkdirs();
        }
        if (!uploadsPicturesDir.exists()) {
            uploadsPicturesDir.mkdirs();
        }
        String[] dirs = pictureDirs.split(",");
        for (String dir : dirs) {
            File imageDir = new File(uploadsPicturesDir, dir);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }
        }
    }


}
