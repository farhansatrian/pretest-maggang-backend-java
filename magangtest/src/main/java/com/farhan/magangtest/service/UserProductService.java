package com.farhan.magangtest.service;

import com.farhan.magangtest.dao.UserProductDao;
import com.farhan.magangtest.dto.UserProductDto;
import com.farhan.magangtest.entity.UserProduct;
import com.farhan.magangtest.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao userProductDao;

    public List<UserProduct> findAll(){
        return this.userProductDao.findAll();
    }

    public UserProduct findById(Integer id){
        return this.userProductDao.findById(id).orElseThrow(() -> new IdNotFoundException("User Product dengan id " + id + " tidak ditemukan"));
    }

    public void save(UserProductDto.save data){
        this.userProductDao.save(data);
    }

    public void delete(Integer id) {
        this.userProductDao.delete(id);
    }

    public void update(Integer id, UserProductDto.update data) {
        findById(id);
        this.userProductDao.update(id,data);
    }
}
