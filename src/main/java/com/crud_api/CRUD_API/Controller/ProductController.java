package com.crud_api.CRUD_API.Controller;
import com.crud_api.CRUD_API.Message;
import com.crud_api.CRUD_API.Product;
import com.crud_api.CRUD_API.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Product> getProductList(){
        return productRepository.getAllProduct();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    Product getProductById(@PathVariable Integer id){
        return productRepository.getProductById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Message createProduct(@RequestBody Product product){
        productRepository.insertProduct(product);
        return new Message("Product Created Sucessfully");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    Message updateProduct(@PathVariable Integer id,@RequestBody Product product){
        productRepository.updateProduct(id,product);
        return new Message("Product Updated Sucessfully");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    Message deleteProduct(@PathVariable Integer id){
        productRepository.deleteProduct(id);
        return new Message("Product Deleted Sucessfully");
    }

}
