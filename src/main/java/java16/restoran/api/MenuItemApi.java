package java16.restoran.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import java16.restoran.dto.request.MenuItemsRequest;
import java16.restoran.dto.response.MenuResponse;
import java16.restoran.entity.MenuItem;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
            @RequestParam("restaurant id") Long restaurantId,
            @RequestParam("sub category id") Long subCategoryId,
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
        menuItem.setSubcategoryId(subCategoryId);
        menuItem.setDescription(description);
        menuItem.setRestaurantId(restaurantId);
        return menuItemService.save(menuItem, file);
    }

    /// get image
    @Secured("CLIENT")
    @GetMapping("/getMenuItem/{id}")
    public MenuResponse getMenuItem(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    //search
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @GetMapping("/search")
    public List<MenuResponse> search(@RequestParam String query) {
       return menuItemService.search(query);
    }

    //getAll sort by price
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @GetMapping("/getAllSortByPrice")
    public List<MenuResponse> getAllSortByPrice(@RequestParam String ascOrDesc) {
        return menuItemService.getAllSortByPrice(ascOrDesc);
    }

    //filter by IsVegetarian
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @GetMapping ("/getAllFilterByVegetarian")
    public List<MenuResponse> getAllFilterByVegetarian(@RequestParam boolean isTrue) {
       return menuItemService.getAllFilterByVegetarian(isTrue);
    }



}

