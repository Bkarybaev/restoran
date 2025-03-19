package java16.restoran.api;

import java16.restoran.dto.response.SubCategoryResponse;
import java16.restoran.entity.Category;
import java16.restoran.entity.SubCategory;
import java16.restoran.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subcategory")
@RequiredArgsConstructor
public class SubCategoryApi {
    private final SubCategoryService subCategoryService;

    //crud
    //save
    @PostMapping("/save/{categoryId}")
    public String save(@PathVariable Long categoryId,
                            @RequestBody String subCategoryName) {
      return subCategoryService.save(categoryId,subCategoryName);

    }

    //get sub category join category
    @GetMapping("/getSubCategory/{categoryId}")
    public List<?> getSubCategory(@PathVariable Long categoryId) {
       return subCategoryService.getByCategoryIdSubCategoryes(categoryId);
    }

    //get group by category
    @GetMapping("/getGroupByCategoryName")
    public Map<String,List<SubCategoryResponse>> getGroupByCategoryName() {
        return subCategoryService.getGroupByIdCategory();
    }

    //delete
    @DeleteMapping("/delete/{subCaId}")
    public String delete(@PathVariable Long subCaId) {
        return subCategoryService.delete(subCaId);
    }

    //update
    @PutMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestBody String subCategoryName) {
       return subCategoryService.update(id,subCategoryName);
    }
}
