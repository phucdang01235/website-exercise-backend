package com.example.mywebsite.repo;

import com.example.mywebsite.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Modifying
    @Query("UPDATE Category c SET c.name = :#{#category.name} WHERE c.id = :#{#category.idCategory}")
    void updateCategoryById(@Param("category") Category category);

}
