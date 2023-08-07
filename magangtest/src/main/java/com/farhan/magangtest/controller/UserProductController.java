package com.farhan.magangtest.controller;


import com.farhan.magangtest.dto.UserProductDto;
import com.farhan.magangtest.entity.UserProduct;
import com.farhan.magangtest.service.UserProductService;
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
@RequestMapping("/api/userproduct")
@RequiredArgsConstructor
public class UserProductController {

    private final UserProductService service;

    @GetMapping
    public ResponseEntity<List<UserProduct>> findAll() {
        List<UserProduct> userProducts = this.service.findAll();
        return ResponseEntity.ok(userProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProduct> findById(
            @PathVariable(name = "id") Integer id
    ) {
        UserProduct userProduct = this.service.findById(id);
        return ResponseEntity.ok(userProduct);
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> save(
            @RequestBody @Valid UserProductDto.save data,
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
        output.put("status","Berhasil menambah UserProduct");
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
    public ResponseEntity<Map<String,Object>> update(
            @PathVariable Integer id,
            @RequestBody @Valid UserProductDto.update data,
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
