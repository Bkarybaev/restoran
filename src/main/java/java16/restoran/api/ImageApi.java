package java16.restoran.api;


import jakarta.annotation.security.PermitAll;
import java16.restoran.service.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class ImageApi {
    @PermitAll
    @GetMapping("/{image}")
    public ResponseEntity<Resource> getImage(@PathVariable String image) {

        Path path = Paths.get(ImageServiceImpl.uploadDir,image);
        Resource resource = UrlResource.from(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found: " + image);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
