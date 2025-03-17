package java16.restoran.service;

import java16.restoran.entity.Category;

import java.util.List;

public interface CategoryService {
    String saveName(String categoryName);

    List<Category> getAll();

    String update(Long id, String categoryName);

    String deleted(Long id);
}
