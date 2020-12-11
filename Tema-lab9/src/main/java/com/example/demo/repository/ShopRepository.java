package com.example.demo.repository;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShopRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Shop> get(String owner_cnp){
        String sql = "SELECT * FROM shop WHERE owner_cnp = ?";
        RowMapper<Shop> mapper = getShopRowMapper();
        return jdbcTemplate.query(sql, mapper, owner_cnp).stream().findAny();
    }

    public void save(Shop shop){
        String sql = "INSERT INTO shop VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, shop.getCui(), shop.getName(), shop.getOwner_cnp());
    }

    private RowMapper<Shop> getShopRowMapper() {
        return (resultSet, i) -> new Shop(
                resultSet.getString("cui"),
                resultSet.getString("name"),
                resultSet.getString("owner_cnp")
        );
    }
}