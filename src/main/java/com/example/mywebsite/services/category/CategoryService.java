package com.example.mywebsite.services.category;


import com.example.mywebsite.entity.Category;

import java.util.Collection;

public interface CategoryService {
     Collection<Category> getAllCategories();

     Category addCategory(Category category);
     Category updateCategory(Category category);
     Boolean removeCategory(Long id);


}
