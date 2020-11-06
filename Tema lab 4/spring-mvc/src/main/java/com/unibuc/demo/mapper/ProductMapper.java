package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Product;
import com.unibuc.demo.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product convertProductFrom(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getProductName())
                .id(productDto.getProductId())
                .price(productDto.getProductPrice())
                .rating(productDto.getProductRating())
                .build();
    }

    public ProductDto convertProductDtoFrom(Product product) {
        return ProductDto.builder()
                .productName(product.getName())
                .productId(product.getId())
                .productPrice(product.getPrice())
                .productRating(product.getRating())
                .build();
    }
}
