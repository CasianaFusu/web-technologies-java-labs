package com.example.demo.service;

import com.example.demo.domain.Shop;
import com.example.demo.exceptions.OwnerNotFoundException;
import com.example.demo.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public void createShop(Shop shop){
        shopRepository.save(shop);
    }

   /* public Shop getShop(String cui){

    }*/

    public Shop getShopByOwnerCnp(String cnp){
        return shopRepository.get(cnp)
                .orElseThrow(() -> new ExpressionException(String.format("Nu a fost gasit niciun magazin.")));
    }
}
