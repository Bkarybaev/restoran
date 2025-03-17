package java16.restoran.api;

import java16.restoran.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategory")
@RequiredArgsConstructor
public class SubCategory {
    private final SubCategoryService subCategoryService;
}
