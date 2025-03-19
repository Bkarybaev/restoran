package java16.restoran.service;

import java16.restoran.dto.response.SubCategoryResponse;
import java16.restoran.entity.Category;
import java16.restoran.entity.SubCategory;

import java.util.List;
import java.util.Map;

public interface SubCategoryService {
    String save(Long categoryId, String subCategoryName);

    List<?> getByCategoryIdSubCategoryes(Long categoryId);

    Map<String,List<SubCategoryResponse>> getGroupByIdCategory();

    String delete(Long subCaId);

    String update(Long id, String subCategoryName);
}
