package com.crud_api.CRUD_API.Repository;

import com.crud_api.CRUD_API.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    @Autowired
    public ProductRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Product> getAllProduct(){
        String sql = "SELECT * FROM product";

        return jdbcClient.sql(sql)
                .query(Product.class)
                .list();
    }

    public Product getProductById(Integer id) {

        String sql = "SELECT * FROM product WHERE id = ?";

        Product product = jdbcClient.sql(sql)
                .param(id)
                .query(Product.class)
                .optional().orElse(null);

        if (product == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product id not found"
            );
        }

        return product;
    }

    public void insertProduct(Product product){

        String sql = "INSERT INTO product (name, price) VALUES (?, ?)";

        jdbcClient.sql(sql)
                .param(product.getName())
                .param(product.getPrice())
                .update();
    }

    public void updateProduct(Integer id, Product product){

        Product thepro = getProductById(id);

        if(thepro != null){

            String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";

            jdbcClient.sql(sql)
                    .param(product.getName())
                    .param(product.getPrice())
                    .param(id)
                    .update();

        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found"
            );
        }
    }

    public void deleteProduct(Integer id){

        Product thepro = getProductById(id);

        if(thepro != null){

            String sql = "DELETE FROM product WHERE id = ?";

            jdbcClient.sql(sql)
                    .param(id)
                    .update();

        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found"
            );
        }
    }
}