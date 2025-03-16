package java16.restoran.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import java16.restoran.dto.request.MenuItemsRequest;
import java16.restoran.entity.MenuItem;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemService menuItemService;


    /// save menu-item
    @Operation(
            summary = "Save images",
            description = "me save image in database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            ))
    @PostMapping(value = "/saveMenuItem",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
            @RequestParam("price") BigDecimal price,
            @RequestParam("isVegetarian") Boolean isVegetarian,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestPart @Parameter(description = "image file") MultipartFile file
    ) throws IOException {
        MenuItemsRequest menuItem = new MenuItemsRequest();
        menuItem.setPrice(price);
        menuItem.setIsVegetarian(isVegetarian);
        menuItem.setName(name);
        menuItem.setDescription(description);

        System.err.println(menuItem);
        return menuItemService.save(menuItem, file);
    }

    /// get image
    @Secured("CLIENT")
    @GetMapping("/getMenuItem/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        return ResponseEntity.ok(menuItem);
    }


}

