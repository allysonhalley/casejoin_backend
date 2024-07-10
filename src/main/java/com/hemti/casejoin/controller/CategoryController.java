package com.hemti.casejoin.controller;

import com.hemti.casejoin.model.category.Category;
import com.hemti.casejoin.model.category.CategoryDTO;
import com.hemti.casejoin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() throws Exception {
        try {
            return ResponseEntity.ok().body(new ArrayList<>(categoryService.findAll()));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDto) throws Exception {
        try {
            Category category = categoryService.save(new Category(categoryDto));
            return ResponseEntity.ok(category);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@RequestParam("id") String id) throws Exception {
        try {
            return ResponseEntity.ok(categoryService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws Exception {
        try {
            return ResponseEntity.ok(categoryService.update(category));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/")
    public ResponseEntity deleteCategory(@RequestBody String id) throws Exception {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Product deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
