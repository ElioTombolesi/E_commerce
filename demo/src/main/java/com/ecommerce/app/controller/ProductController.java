
package com.ecommerce.app.controller;
import com.ecommerce.app.entities.Product;
import com.ecommerce.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.loadProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (productService.loadProductById(id) != null) {
            productService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product product) {
        ResponseEntity<Product> response = null;

        Optional<Product> userDetails = productService.loadProductById(product.getId());
        if (userDetails != null) {
            response = ResponseEntity.ok(productService.update(product));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Product product)
    {
        var ProductSaved = productService.create(product);
        return new ResponseEntity(HttpStatus.OK);
    }
}
