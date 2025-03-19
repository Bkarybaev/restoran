package java16.restoran.api;

import java16.restoran.dto.response.CategoryResponse;
import java16.restoran.entity.Category;
import java16.restoran.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    //crud
    //create
    @PreAuthorize("hasAnyAuthority('ADMIN,POVAR')")
    @PostMapping("/save")
    public String save(@RequestBody String categoryName) {
        return categoryService.saveName(categoryName);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN,POVAR')")
    @GetMapping("/getAllCategoryes")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAll();
    }

    @Secured("ADMIN")
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody String categoryName) {
        return categoryService.update(id, categoryName);
    }
    @Secured("ADMIN")
    @DeleteMapping("/deletetById/{id}")
    public String delete(@PathVariable Long id) {
        return categoryService.deleted(id);
    }
}
