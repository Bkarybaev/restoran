package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.request.MenuItemsRequest;
import java16.restoran.entity.MenuItem;
import java16.restoran.exceptions.NotFount;
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
        MenuItem res = new MenuItem();
        res.setName(menuItem.getName());
        res.setDescription(menuItem.getDescription());
        res.setPrice(menuItem.getPrice());
        res.setIsVegetarian(menuItem.getIsVegetarian());
        res.setImage(fileName);
        MenuItem save = menuItemRepo.save(res);
        System.err.println(save);
        return ResponseEntity.ok(res);
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepo.findById(id).orElseThrow(()
                -> new NotFount("Menu item not found by id " + id));
    }
}
