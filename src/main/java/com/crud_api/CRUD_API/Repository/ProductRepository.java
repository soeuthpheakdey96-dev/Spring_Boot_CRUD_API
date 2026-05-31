package com.crud_api.CRUD_API.Repository;
import com.crud_api.CRUD_API.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    @Autowired
    public ProductRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Product> getAllProduct(){
        String sql = "SELECT * FROM Product";
        return jdbcClient.sql(sql)
                .query(Product.class)
                .list();
    }

    public Product getProductById(Integer id){
        String sql = "SELECT * FROM Product WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(id)
                .query(Product.class)
                .single();
    }

    public void insertProduct(Product product){
        String sql = "INSERT INTO Product (id, name, price) VALUES (?, ?, ?)";
        jdbcClient.sql(sql)
                .param(product.getId())
                .param(product.getName())
                .param(product.getPrice())
                .update();
    }

    public void  updateProduct(Integer id,Product product){
        String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        jdbcClient.sql(sql)
                .param(product.getName())
                .param(product.getPrice())
                .param(id)
                .update();
    }

    public void deleteProduct(Integer id){
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcClient.sql(sql)
                .param(id)
                .update();
    }
}