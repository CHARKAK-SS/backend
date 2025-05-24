package charkak.demo.controller;

import charkak.demo.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileUploadController {

    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        System.out.println("📥 업로드 요청 들어옴: " + file.getOriginalFilename());
        try {
            String imageUrl = s3Uploader.upload(file, "calendar");
            System.out.println("✅ 업로드 성공: " + imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            System.out.println("❌ 업로드 실패: " + e.getMessage());
            return ResponseEntity.status(500).body("이미지 업로드 실패: " + e.getMessage());
        }
    }

}
