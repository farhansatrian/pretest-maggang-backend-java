package com.farhan.magangtest.service;

import com.farhan.magangtest.dao.CategoryDao;
import com.farhan.magangtest.dto.CategoryDto;
import com.farhan.magangtest.entity.Category;
import com.farhan.magangtest.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao categoryDao;

    public List<Category> findAll() {
        return this.categoryDao.findAll();
    }

    public Category findById(Integer id) {
        return this.categoryDao.findById(id).orElseThrow(() -> new IdNotFoundException("Category dengan id " + id + " tidak ditemukan"));
    }

    public void save(CategoryDto.save data){
        this.categoryDao.save(data);
    }

    public void update(Integer id, CategoryDto.update data) {
        findById(id);
        this.categoryDao.update(id,data);
    }

    public void delete(Integer id) {
        this.categoryDao.delete(id);
    }
}
