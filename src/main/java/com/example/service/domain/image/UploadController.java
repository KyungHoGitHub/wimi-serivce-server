package com.example.service.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/api/group/image")
    public ResponseEntity<Map<String,String>> uploadImage(@RequestParam("file")MultipartFile file)throws IOException{

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path savePath = Paths.get(uploadDir, fileName);

        Files.createDirectories(savePath.getParent());
        file.transferTo(savePath.toFile());

        String imageUrl = serverUrl + "/images/" + fileName;
        return ResponseEntity.ok(Map.of("imageUrl",imageUrl));
    }
}
