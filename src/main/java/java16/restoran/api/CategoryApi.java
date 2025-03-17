package java16.restoran.api;

import java16.restoran.entity.Category;
import java16.restoran.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    //crud
    //create
    @PostMapping("/save")
    public String save(@RequestBody String categoryName) {
       return categoryService.saveName(categoryName);
    }

    @GetMapping("/getAllCategoryes")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody String categoryName) {
        return categoryService.update(id,categoryName);
    }

    @DeleteMapping("/deletetById/{id}")
    public String delete(@PathVariable Long id) {
       return categoryService.deleted(id);
    }
}
