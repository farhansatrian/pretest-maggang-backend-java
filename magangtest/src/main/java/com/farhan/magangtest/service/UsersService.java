package com.farhan.magangtest.service;

import com.farhan.magangtest.dao.UserProductDao;
import com.farhan.magangtest.dao.UsersDao;
import com.farhan.magangtest.dto.UsersDto;
import com.farhan.magangtest.entity.Users;
import com.farhan.magangtest.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersDao userDao;

    private final UserProductDao userProductDao;

    public List<Users> findAll() {
        return this.userDao.findAll();
    }

    public Users findById(Integer id){
        return this.userDao.findById(id).orElseThrow(() -> new IdNotFoundException("User dengan id " + id + " tidak ditemukan"));
    }

    public void save(UsersDto.save data) {
        this.userDao.save(data);
    }

    public void update(Integer id, UsersDto.update data) {
        findById(id);
        this.userDao.update(id,data);
    }

    public void delete(Integer id) {
        this.userDao.delete(id);
        this.userProductDao.deleteByUser(id);
    }
}
