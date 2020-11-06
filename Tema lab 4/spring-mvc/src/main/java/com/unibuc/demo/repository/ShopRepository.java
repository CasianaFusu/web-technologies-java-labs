package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Product;
import com.unibuc.demo.domain.Shop;
import com.unibuc.demo.dto.ShopDto;
import com.unibuc.demo.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShopRepository {

    private final ProductRepository productRepository;
    private final List<Shop> shopList = new ArrayList<>();

    @Value("${products.prefixId}")
    private String idPrefix;

    @Value("${shop.one.start.range}")
    private int shopOneStartRange;
    @Value("${shop.one.end.range}")
    private int shopOneEndRange;
    @Value("${shop.two.start.range}")
    private int shopTwoStartRange;
    @Value("${shop.two.end.range}")
    private int shopTwoEndRange;
    private ShopDto shoDtop;

    @Autowired
    public ShopRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Shop> getAll() {
        return shopList;
    }

    public Optional<Shop> getByCui(String cui) {
        return shopList.stream().filter(shop -> shop.getCUI().equals(cui)).findFirst();
    }

    private List<Product> retrieveProductsFromProductRepository(int startRange, int endRange) {
        List<Product> products = new ArrayList<>();
        for (int i = startRange; i < endRange; i++) {
            Optional<Product> productById = productRepository.getProductById(idPrefix + i);
            productById.ifPresent(products::add);
        }
        return products;
    }

    private void createAndSave(String name, String cui, List<Product> products) {
        Shop shop = Shop.builder()
                .name(name)
                .CUI(cui)
                .productList(products)
                .build();

        shopList.add(shop);
    }


    public void createShopPublic(String name, String cui)
    {
        Shop shop = Shop.builder()
                .name(name)
                .CUI(cui)
                .productList(retrieveProductsFromProductRepository(shopOneStartRange, shopOneEndRange))
                .build();

        shopList.add(shop);
    }


    public void updateShop(ShopDto dto)
    {
        var shopMapper = new ShopMapper();
        Shop shop =  shopMapper.convertShopFrom(dto);

        shopList.set(shopList.indexOf(getByCui(dto.getShopCUI()).get()),shop);
    }

    private void setUpContextForShopRepository() {
        createAndSave("Lidl", "1111", retrieveProductsFromProductRepository(shopOneStartRange, shopOneEndRange));
        createAndSave("Mega", "2222", retrieveProductsFromProductRepository(shopTwoStartRange, shopTwoEndRange));
    }

    @PostConstruct
    public void afterPropertiesSetting() {
        setUpContextForShopRepository();
    }

}
