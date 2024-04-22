package com.example.lab1b.web;

import com.example.lab1b.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/category")
public class CategoryRestController {

    @GetMapping()
    public ResponseEntity<Category[]> getAll() {
        Category[] categories = Category.values();
        return ResponseEntity.ok(categories);
    }
}
