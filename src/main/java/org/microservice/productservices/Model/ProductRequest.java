package org.microservice.productservices.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;
}
