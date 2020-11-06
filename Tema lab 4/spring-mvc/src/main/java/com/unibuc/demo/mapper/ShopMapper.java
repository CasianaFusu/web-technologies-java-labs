package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Product;
import com.unibuc.demo.domain.Shop;
import com.unibuc.demo.dto.ProductDto;
import com.unibuc.demo.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopMapper {

    @Autowired
    private ProductMapper productMapper;

    public Shop convertShopFrom(ShopDto shopDto) {
        List<Product> productsConverted = new ArrayList<>();
        if (shopDto.getShopProductList() != null) {
            shopDto.getShopProductList().forEach(productDto -> productsConverted.add(productMapper.convertProductFrom(productDto)));
        }
        return Shop.builder()
                .name(shopDto.getShopName())
                .CUI(shopDto.getShopCUI())
                .productList(productsConverted)
                .build();
    }

    public ShopDto convertShopFrom(Shop shop) {
        List<ProductDto> productDtos = new ArrayList<>();
        shop.getProductList().forEach(product -> productDtos.add(productMapper.convertProductDtoFrom(product)));
        return ShopDto.builder()
                .shopName(shop.getName())
                .shopCUI(shop.getCUI())
                .shopProductList(productDtos)
                .build();
    }
}
