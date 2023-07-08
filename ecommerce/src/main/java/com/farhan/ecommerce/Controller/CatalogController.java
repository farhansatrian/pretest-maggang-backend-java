package com.farhan.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farhan.ecommerce.entity.Catalog;
import com.farhan.ecommerce.service.CatalogService;

@RestController
@RequestMapping("/api")

@PreAuthorize("isAuthenticated()")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalogs")
    public List<Catalog> findAll() {
        return catalogService.findAll();
    }

    @GetMapping("/catalogs/{id}")
    public Catalog findById(@PathVariable("id") String id) {
        return catalogService.findById(id);
    }

    @PostMapping("/catalogs")
    public Catalog create(@RequestBody Catalog catalog) {
        return catalogService.create(catalog);
    }

    @PutMapping("/catalogs")
    public Catalog edit(@RequestBody Catalog catalog) {
        return catalogService.edit(catalog);
    }

    @DeleteMapping("/catalogs/{id}")
    public void deleteById(@PathVariable("id") String id) {
        catalogService.deleteById(id);
    }

}
