package org.microservice.productservices.Services;

import org.microservice.productservices.Model.ProductRequest;
import org.microservice.productservices.Model.ProductResponse;

public interface ProductServices {

  long addProduct(ProductRequest productRequest);

  ProductResponse getProductById(long productId);

  void reduceQuantity(long productId, long quantity);
}
