package java16.restoran.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java16.restoran.dto.MenuItemsRequest;
import java16.restoran.entity.MenuItem;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemService menuItemService;


    /// save menu-item
        @Operation(
               summary = "Save images",
               description = "me save image in database",
               requestBody = @RequestBody(
                       content = @Content(
                               mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                       )
               ))
    @PostMapping(value = "/saveMenuItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
                @ModelAttribute MenuItemsRequest menuItems,
                @RequestPart(value = "file", required = false)
                @Parameter(description = "image file") MultipartFile file
                )throws IOException{
            System.err.println(menuItems);
           return menuItemService.save(menuItems, file);
        }

        /// get image
        @Secured("CLIENT")
        @GetMapping("/getMenuItem/{id}")
        public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
            MenuItem menuItem = menuItemService.getMenuItemById(id);
            return ResponseEntity.ok(menuItem);
        }



}

