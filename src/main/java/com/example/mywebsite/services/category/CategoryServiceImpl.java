package com.example.mywebsite.services.category;

import com.example.mywebsite.entity.Category;
import com.example.mywebsite.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Collection<Category> getAllCategories() {
        log.info("getAllCategories");
        return categoryRepo.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        log.info("Adding category " + category.getName());
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        log.info("Updating category by id: " + category.getIdCategory());
        categoryRepo.updateCategoryById(category);
        return category;
    }

    @Override
    public Boolean removeCategory(Long id) {
        log.info("Removing category by id: " + id);
        categoryRepo.deleteById(id);
        return true;
    }
}
