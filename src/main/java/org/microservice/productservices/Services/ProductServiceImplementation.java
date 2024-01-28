package org.microservice.productservices.Services;

import lombok.extern.log4j.Log4j2;
import org.microservice.productservices.Entity.Product;
import org.microservice.productservices.Exceptions.ProductServiceCustomException;
import org.microservice.productservices.Model.ProductRequest;
import org.microservice.productservices.Model.ProductResponse;
import org.microservice.productservices.Repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImplementation implements ProductServices{

    @Autowired
    private ProductRepository productRepository;

    //@Autowired
    //private Product product;

    //@Autowired
    //private ProductResponse productResponse;

    @Override
    public long addProduct(ProductRequest productRequest) {

        log.info("Create or Add Products here...");

        Product product =
                Product.builder()
                        .productName(productRequest.getName())
                        .quantity(productRequest.getQuantity())
                        .price(productRequest.getPrice())
                        .build();
            productRepository.save(product);

            log.info("Product Created....");
        return product.getProductID();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product identity:", productId);

       Product product = productRepository.findById(productId)
               .orElseThrow(
                       ()-> new ProductServiceCustomException("Product with the provided identity not found, Please check the ID....", "PRODUCT_NOT_FOUND"));

         ProductResponse productResponse =
        new ProductResponse();

        copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for ID: {}", quantity, productId);

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() -> new ProductServiceCustomException(
                           "Product with given ID NOT FOUND",
                           "PRODUCT_NOT_FOUND"
                        ));
            if(product.getQuantity() < quantity) {
                throw new ProductServiceCustomException(
                  "Product does not have sufficient Quantity",
                  "Insufficient Quantity"
                );
            }

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            log.info("Product quantity updated successfully....");
    }


}
