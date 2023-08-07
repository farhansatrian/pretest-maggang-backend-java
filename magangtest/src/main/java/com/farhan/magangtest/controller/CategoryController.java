package com.farhan.magangtest.controller;

import com.farhan.magangtest.dto.CategoryDto;
import com.farhan.magangtest.entity.Category;
import com.farhan.magangtest.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> category = this.service.findAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(
            @PathVariable(name = "id") Integer id
    ) {
        Category category = this.service.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> save(
            @RequestBody @Valid CategoryDto.save data,
            BindingResult result
    ) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String,Object> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.save(data);
        output.put("status","Berhasil menambah Category");
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> delete(
            @PathVariable Integer id
    ) {
        this.service.delete(id);
        Map<String,Object> output = new HashMap<>();
        output.put("status", "Berhasil menghapus data");
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Map<String,Object>> update(
            @PathVariable Integer id,
            @RequestBody @Valid CategoryDto.update data,
            BindingResult result
    ) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String,Object> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.update(id,data);
        output.put("status","Berhasil Update Data");
        return ResponseEntity.ok(output);
    }

}
