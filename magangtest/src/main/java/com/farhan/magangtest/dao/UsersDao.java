package com.farhan.magangtest.dao;

import com.farhan.magangtest.dto.UsersDto;
import com.farhan.magangtest.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Users> findAll() {
        String query = "SELECT id, \"name\", email, phone, address\n" +
                "FROM public.users order by id asc;\n";

        return this.jdbcTemplate.query(query, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        });
    }

    public Optional<Users> findById(Integer id) {
        String query = "SELECT id, \"name\", email, phone, address\n" +
                "FROM public.users where id =:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Users>>() {
                @Override
                public Optional<Users> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    return Optional.of(user);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(UsersDto.save inputData) {
        String query = "INSERT INTO public.users\n" +
                "(\"name\", email, phone, address)\n" +
                "VALUES(:name, :email, :phone,:address);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", inputData.getName());
        map.addValue("email", inputData.getEmail());
        map.addValue("phone", inputData.getPhone());
        map.addValue("address", inputData.getAddress());

        this.jdbcTemplate.update(query,map);
    }

    public void update (Integer id, UsersDto.update editData) {
        String query = "UPDATE public.users\n" +
                "SET \"name\"=:name, email=:email, phone=:phone, address=:address\n" +
                "WHERE id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", editData.getName());
        map.addValue("email", editData.getEmail());
        map.addValue("phone", editData.getPhone());
        map.addValue("address", editData.getAddress());

        this.jdbcTemplate.update(query, map);
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.users\n" +
                "WHERE id=:id;\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }

}
