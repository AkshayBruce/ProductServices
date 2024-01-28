package org.microservice.productservices.Controller;

import org.microservice.productservices.Model.ProductRequest;
import org.microservice.productservices.Model.ProductResponse;
import org.microservice.productservices.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest)
    {
        long productId =productServices.addProduct(productRequest);

        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId)
    {
        ProductResponse     productResponse =
                productServices.getProductById(productId);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity)
    {
        productServices.reduceQuantity(productId, quantity);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
