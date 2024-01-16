package com.example.mywebsite.controller;

import com.example.mywebsite.entity.Category;
import com.example.mywebsite.model.Response;
import com.example.mywebsite.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<Response> getCategories(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Categories received")
                        .data(Map.of("objects", categoryService.getAllCategories()))
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addCategory(@RequestBody @Valid Category category){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED)
                        .message("Added category")
                        .data(Map.of("object", categoryService.addCategory(category)))
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Updated category")
                        .data(Map.of("object", categoryService.updateCategory(category)))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Deleted category")
                        .data(Map.of("object", categoryService.removeCategory(id)))
                        .build()
        );
    }

}
