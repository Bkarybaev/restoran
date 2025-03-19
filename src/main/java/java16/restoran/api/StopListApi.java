package java16.restoran.api;

import java16.restoran.dto.response.MenuResponse;
import java16.restoran.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/stopList")
@RequiredArgsConstructor
public class StopListApi {
    private final StopListService stopListService;


    @PostMapping("/saveMenu-item/{menuId}")
    public String saveMenuItem(@PathVariable Long menuId, @RequestBody String reason) {
       return stopListService.save(menuId,reason);
    }

    @PostMapping("/deleteMenu-item/{menuId}")
    public String deleteMenuItem(@PathVariable Long menuId) {
        return stopListService.delete(menuId);
    }
}
