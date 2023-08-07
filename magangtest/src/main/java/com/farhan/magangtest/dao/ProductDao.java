package com.farhan.magangtest.dao;

import com.farhan.magangtest.dto.ProductDto;
import com.farhan.magangtest.entity.Product;
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
public class ProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public List<Product> findAll() {
        String query = """
SELECT p.id, p.name, category_id, c.name as category_name, p.stock, p.description, p.price, p.image FROM public.products p join category c on c.id = p.category_id order by id asc;
""";

        return this.jdbcTemplate.query(query, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setCategory_name(rs.getString("category_name"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                return product;
            }
        });
    }

//    public List<Product> findByCategoryId(Integer id) {
//        String query = """
//SELECT p.id, p.name, category_id, c.name as category_name, p.stock, p.description, p.price, p.image FROM public.products p join category c on c.id = p.category_id where category_id = :id order by id asc;
//""";
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        map.addValue("id",id);
//
//        return this.jdbcTemplate.query(query, new RowMapper<Product>() {
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setName(rs.getString("name"));
//                product.setCategory_id(rs.getInt("category_id"));
//                product.setCategory_name(rs.getString("category_name"));
//                product.setStock(rs.getInt("stock"));
//                product.setDescription(rs.getString("description"));
//                product.setPrice(rs.getInt("price"));
//                product.setImage(rs.getString("image"));
//
//                return product;
//            }
//        });
//    }

    public Optional<Product> findById(Integer id) {
        String query = "SELECT id, \"name\", category_id, stock, description, price, image\n" +
                "FROM public.products where id =:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Product>>() {
                @Override
                public Optional<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setStock(rs.getInt("stock"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getInt("price"));
                    product.setImage(rs.getString("image"));
                    return Optional.of(product);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(ProductDto.save inputData){
        String query = "INSERT INTO public.products\n" +
                "(\"name\", category_id, stock, description, price, image)\n" +
                "VALUES(:name, :category_id, :stock, :description, :price, :image);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", inputData.getName());
        map.addValue("category_id", inputData.getCategory_id());
        map.addValue("stock", inputData.getStock());
        map.addValue("description",inputData.getDescription());
        map.addValue("price",inputData.getPrice());
        map.addValue("image", inputData.getImage());

        this.jdbcTemplate.update(query, map);

    }

    public void update(Integer id,ProductDto.update inputData){
        String query = "UPDATE public.products\n" +
                "SET \"name\"=:name, category_id=:category_id, stock=:stock, description=:description, price=:price, image=:image\n" +
                "WHERE id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", inputData.getName());
        map.addValue("category_id", inputData.getCategory_id());
        map.addValue("stock", inputData.getStock());
        map.addValue("description",inputData.getDescription());
        map.addValue("price",inputData.getPrice());
        map.addValue("image", inputData.getImage());

        this.jdbcTemplate.update(query, map);
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.products\n" +
                "WHERE id=:id;\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }

}
