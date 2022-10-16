package com.fararuNews.category.rest;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.category.application.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "category", path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(name = "category", path = "/find-by-category/{category}")
    public ResponseEntity findByCategory(@PathVariable(name = "category") String category) {

        List<RegisterWebSite> registerWebSite = categoryService.findByCategory(category);
        return new ResponseEntity(registerWebSite, HttpStatus.OK);

    }
}

