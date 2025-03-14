package java16.restoran.api;

//import java16.restoran.dto.MenuItemsRequest;
//import java16.restoran.entity.MenuItem;
//import java16.restoran.service.MenuItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/menu-item")
//public class MenuItemApi {
//    private final MenuItemService menuItemService;
//
//    @PostMapping("/saveMenuItem")
//    public ResponseEntity<?> saveMenuItem(
//            @RequestBody MenuItemsRequest menuItem) throws IOException {
//       return menuItemService.save(menuItem);
//    }
//}

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java16.restoran.dto.MenuItemsRequest;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemService menuItemService;
    @Operation(
            summary = "Save menu item",
            description = "Saves a menu item with an optional image file",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            )
    )

    @PostMapping(value = "/saveMenuItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveMenuItem(
            @RequestBody MenuItemsRequest menuItem,
            @RequestPart(value = "file", required = false) @Parameter(description = "Image file") MultipartFile file) throws IOException {

        return menuItemService.save(menuItem, file);
    }
}

