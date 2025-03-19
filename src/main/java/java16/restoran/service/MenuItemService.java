package java16.restoran.service;

import java16.restoran.dto.request.MenuItemsRequest;
import java16.restoran.dto.response.MenuResponse;
import java16.restoran.entity.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuItemService {
    ResponseEntity<?> save(MenuItemsRequest menuItem, MultipartFile file) throws IOException;

    MenuResponse getMenuItemById(Long id);


    List<MenuResponse> search(String query);

    List<MenuResponse> getAllSortByPrice(String acsOrDesc);

    List<MenuResponse> getAllFilterByVegetarian(boolean isTrue);
}
