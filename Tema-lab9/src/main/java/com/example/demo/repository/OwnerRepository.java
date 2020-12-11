package com.example.demo.repository;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.OwnerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OwnerRepository(){
        ;
    }

    public Optional<Owner> get(String cnp){
        String sql = "SELECT * FROM owner o join shop s on o.cnp = s.owner_cnp  WHERE cnp = ?";
        RowMapper<Owner> mapper = getOwnerShopRowMapper();
        return jdbcTemplate.query(sql, mapper, cnp).stream().findAny();
    }

    public List<Owner> getAll(){
        String sql = "SELECT * FROM owner o join shop s on o.cnp = s.owner_cnp";
        RowMapper<Owner> mapper = getOwnerShopRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void save(Owner owner) {
        String sql = "INSERT INTO owner VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, owner.getCnp(), owner.getHasShop(), owner.getNume());
    }

   public String delete(String cnp){
        String sql = "DELETE FROM owner WHERE cnp = ?";
        jdbcTemplate.update(sql, cnp);
        return "Proprietarul a fost sters";
    }

    public void update(String cnp, String nume){
        String sql = "UPDATE owner SET nume = ? WHERE cnp = ?";
        jdbcTemplate.update(sql,nume,cnp);
    }

    private RowMapper<Owner> getOwnerRowMapper() {
        return (resultSet, i) -> new Owner(
                resultSet.getString("cnp"),
                resultSet.getBoolean("hasShop"),
                resultSet.getString("nume"),
                null
        );
    }

    private RowMapper<Owner> getOwnerShopRowMapper() {
        return (resultSet, i) -> new Owner(
                resultSet.getString("cnp"),
                resultSet.getBoolean("hasShop"),
                resultSet.getString("nume"),
                new Shop(
                        resultSet.getString("cui"),
                        resultSet.getString("name"),
                        resultSet.getString("cnp")
                )
        );
    }
}
