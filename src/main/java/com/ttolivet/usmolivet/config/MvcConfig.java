package com.ttolivet.usmolivet.config;

import com.ttolivet.usmolivet.UsmolivetApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${app.uploads.dir}")
    private String uploads;

    Path currentPath = new File (UsmolivetApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toPath();
    String baseDir = currentPath.getParent().getParent().getParent().toString().replaceFirst("file:", "");

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + baseDir + File.separator + uploads + File.separator);
    }

    @Bean("baseDir")
    public String getBaseDir() {
        return baseDir;
    }

}