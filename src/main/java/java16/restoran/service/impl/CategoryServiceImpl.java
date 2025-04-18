package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.response.CategoryResponse;
import java16.restoran.entity.Category;
import java16.restoran.exceptions.NotFount;
import java16.restoran.repo.CategoryRepo;
import java16.restoran.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public String saveName(String categoryName) {
        if (Objects.equals(categoryName, "") || categoryName.equalsIgnoreCase(" ")) {
            throw new NullPointerException("categoryName is null");
        }
        Category category = new Category();
        category.setName(categoryName);
        categoryRepo.save(category);
        return "Saved";
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepo.findAll().stream()
                .map(category -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setCategoryId(category.getId());
                    response.setName(category.getName()); 
                    return response;
                })
                .collect(Collectors.toList());
    }


    @Override
    public String update(Long id, String categoryName) {
        Category byId = categoryRepo.findById(id)
                .orElseThrow(()-> new NotFount("category not found with id: " + id));
        byId.setName(categoryName);
         categoryRepo.save(byId);
         return "Updated";
    }

    @Override
    public String deleted(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new NotFount("category not found with id: " + id));
        categoryRepo.delete(category);
        return "success";
    }
}
