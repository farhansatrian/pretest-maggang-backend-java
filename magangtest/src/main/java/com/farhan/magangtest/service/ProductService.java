package com.farhan.magangtest.service;

import com.farhan.magangtest.dao.ProductDao;
import com.farhan.magangtest.dto.ProductDto;
import com.farhan.magangtest.entity.Product;
import com.farhan.magangtest.entity.Users;
import com.farhan.magangtest.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> findAll(){
        return this.productDao.findAll();
    }

//    public List<Product> findByCategoryId(Integer categoryId) {
//        return this.productDao.findByCategoryId(categoryId);
//    }

    public Product findById(Integer id){
        return this.productDao.findById(id).orElseThrow(() -> new IdNotFoundException("Product dengan id " + id + " tidak ditemukan"));
    }

    public void save(ProductDto.save data){
        this.productDao.save(data);
    }

    public void update(Integer id, ProductDto.update data) {
        findById(id);
        this.productDao.update(id,data);
    }

    public void delete(Integer id) {
        this.productDao.delete(id);
    }

}
