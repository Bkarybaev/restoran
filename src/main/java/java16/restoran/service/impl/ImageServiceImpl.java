package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final String uploadDir = "/Users/baiel/Documents/imagesRestauran/";
    @Override
    public String save(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, file.getBytes());
        return fileName;
    }
}
