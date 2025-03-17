package java16.restoran.service.impl;

import java16.restoran.repo.SubCategoryRepo;
import java16.restoran.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategorySerImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
}
