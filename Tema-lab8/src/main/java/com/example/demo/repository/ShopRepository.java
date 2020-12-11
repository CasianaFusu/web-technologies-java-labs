package com.example.demo.repository;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShopRepository {
    private final List<Shop> shopList = new ArrayList<>();

    public ShopRepository() {
        initShopList();
    }

    public Optional<Shop> get(String cui) {
        return shopList.stream().filter(shop -> shop.getCui().equals(cui)).findFirst();
    }

    public List<Shop> getAll() {
        return shopList;
    }

    public Shop save(Shop request) {
        shopList.add(request);
        return request;
    }

    public String delete(String cui) {
        Optional<Shop> ownerOptional = get(cui);
        if (ownerOptional.isPresent()) {
            shopList.remove(ownerOptional.get());
            return "Deleted shop";
        }
        return "Didn't find this shop";
    }

    public Shop update(Shop request) {
        Optional<Shop> ownerOptional = get(request.getCui());
        if (ownerOptional.isPresent()) {
            shopList.remove(ownerOptional.get());
            shopList.add(request);
            return get(request.getCui()).get();
        }
        return null;
    }

    private void initShopList() {
        shopList.add(Shop.builder()
                .cui("43534534")
                .name("Lidl")
                .build());

        shopList.add(Shop.builder()
                .cui("534534534534")
                .name("Mega")
                .build());
    }
}