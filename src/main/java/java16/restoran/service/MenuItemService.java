package java16.restoran.service;

import java16.restoran.dto.MenuItemsRequest;
import java16.restoran.entity.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MenuItemService {
    ResponseEntity<?> save(MenuItemsRequest menuItem, MultipartFile file) throws IOException;
}
