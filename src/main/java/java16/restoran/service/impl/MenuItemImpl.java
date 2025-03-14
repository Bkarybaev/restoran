package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.MenuItemsRequest;
import java16.restoran.entity.MenuItem;
import java16.restoran.repo.MenuItemRepo;
import java16.restoran.service.ImageService;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemImpl implements MenuItemService {
    private final ImageService imageService;
    private final MenuItemRepo menuItemRepo;

    @Override
    public ResponseEntity<?> save(MenuItemsRequest menuItem, MultipartFile file) throws IOException {
       String fileName = imageService.save(file);
        MenuItem menuItem1 = menuItem.newMenuItem();
        menuItem1.setImage(fileName);
        menuItemRepo.save(menuItem1);
        return null;
    }
}
