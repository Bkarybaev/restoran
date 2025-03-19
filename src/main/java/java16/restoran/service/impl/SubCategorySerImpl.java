package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.response.SubCategoryResponse;
import java16.restoran.entity.Category;
import java16.restoran.entity.SubCategory;
import java16.restoran.exceptions.NotFount;
import java16.restoran.repo.CategoryRepo;
import java16.restoran.repo.SubCategoryRepo;
import java16.restoran.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategorySerImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    @Override
    @Transactional
    public String save(Long categoryId, String subCategoryName) {
        Category category = categoryRepo.findByIdException(categoryId);
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryName);
        subCategory.setCategory(category);
        category.getSubCategories().add(subCategory);
        categoryRepo.save(category);
        subCategoryRepo.save(subCategory);
        return "saved";
    }

    @Override
    public List<?> getByCategoryIdSubCategoryes(Long categoryId) {
        Category category = categoryRepo.findByIdException(categoryId);
        List<SubCategory> subCategories = category.getSubCategories();
        List<SubCategoryResponse> responses = new ArrayList<>();

        for (SubCategory subCategory : subCategories) {
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            subCategoryResponse.setName(subCategory.getName());
            subCategoryResponse.setId(subCategory.getId());
            responses.add(subCategoryResponse);
        }
        return responses.stream()
                .sorted(Comparator.comparing(SubCategoryResponse::getName))
                .toList();
    }

    @Override
    public Map<String, List<SubCategoryResponse>> getGroupByIdCategory() {
        List<SubCategory> allWithCategory = subCategoryRepo.findAllWithCategory();
        return allWithCategory.stream()
                .collect(Collectors.groupingBy(subCategory ->
                                subCategory.getCategory().getName(),
                        Collectors.mapping(subCategory -> {
                            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
                            subCategoryResponse
                                    .setName(subCategory.getName());
                            subCategoryResponse
                                    .setId(subCategory.getId());
                            return subCategoryResponse;
                        }, Collectors.toList())
                ));


    }

    @Override
    public String delete(Long subCaId) {
        subCategoryRepo.delete(subCategoryRepo.findByIdException(subCaId));
        return "deleted";
    }

    @Override
    public String update(Long id, String subCategoryName) {
        SubCategory subCategory = subCategoryRepo.findByIdException(id);
        subCategory.setName(subCategoryName);
        subCategoryRepo.save(subCategory);
        return "success updated";
    }
}
