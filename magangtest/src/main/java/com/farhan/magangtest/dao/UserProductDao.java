package com.farhan.magangtest.dao;

import com.farhan.magangtest.dto.UserProductDto;
import com.farhan.magangtest.entity.UserProduct;
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
public class UserProductDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<UserProduct> findAll() {
        String query = "SELECT \n" +
                "    up.id, \n" +
                "    up.user_id, \n" +
                "    up.product_id, \n" +
                "    up.quantity,\n" +
                "    u.name AS user_name, \n" +  // Alias untuk name dari tabel users
                "    p.name AS product_name\n" +  // Alias untuk name dari tabel products
                "FROM \n" +
                "    public.user_product AS up\n" +
                "JOIN \n" +
                "    public.users AS u ON up.user_id = u.id\n" +
                "JOIN \n" +
                "    public.products AS p ON up.product_id = p.id;\n";

        return this.jdbcTemplate.query(query, new RowMapper<UserProduct>() {
            @Override
            public UserProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserProduct userProduct = new UserProduct();
                userProduct.setId(rs.getInt("id"));
                userProduct.setUser_id(rs.getInt("user_id"));
                userProduct.setProduct_id(rs.getInt("product_id"));
                userProduct.setQuantity(rs.getInt("quantity"));
                userProduct.setUser_name(rs.getString("user_name"));  // Menggunakan alias user_name
                userProduct.setProduct_name(rs.getString("product_name"));  // Menggunakan alias product_name
                return userProduct;
            }
        });
    }


    public Optional<UserProduct> findById(Integer id) {
        String query = "SELECT id, user_id, product_id, quantity\n" +
                "FROM public.user_product where id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<UserProduct>>() {
                @Override
                public Optional<UserProduct> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserProduct userProduct = new UserProduct();
                    userProduct.setId(rs.getInt("id"));
                    userProduct.setUser_id(rs.getInt("user_id"));
                    userProduct.setProduct_id(rs.getInt("product_id"));
                    userProduct.setQuantity(rs.getInt("quantity"));
                    return Optional.of(userProduct);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(UserProductDto.save inputData){
        String query = "INSERT INTO public.user_product\n" +
                "(user_id, product_id, quantity)\n" +
                "VALUES(:user_id, :product_id, :quantity);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user_id", inputData.getUser_id());
        map.addValue("product_id", inputData.getProduct_id());
        map.addValue("quantity", inputData.getQuantity());

        this.jdbcTemplate.update(query, map);

    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.user_product\n" +
                "WHERE id=:id;\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }

    public void deleteByUser(Integer id) {
        String query = """
               DELETE FROM public.user_product WHERE user_id=:id
                """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        this.jdbcTemplate.update(query, map);
    }

    public void update (Integer id, UserProductDto.update editData) {
        String query = "UPDATE public.user_product\n" +
                "SET user_id=:user_id, product_id=:product_id, quantity=:quantity\n" +
                "WHERE id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("user_id", editData.getUser_id());
        map.addValue("product_id", editData.getProduct_id());
        map.addValue("quantity", editData.getQuantity());

        this.jdbcTemplate.update(query, map);
    }


}
