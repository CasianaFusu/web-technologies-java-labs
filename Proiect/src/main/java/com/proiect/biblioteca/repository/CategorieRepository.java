package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CategorieRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategorieRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Categorie> findAll(){
        String sql = "SELECT * FROM categorii";

        RowMapper<Categorie> mapper = ((resultSet, rowNumber) ->
                new Categorie(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }

    public Optional<Categorie> findById(int id){
        String sql = "SELECT * FROM categorii WHERE id = ?";

        RowMapper<Categorie> mapper = ((resultSet, rowNumber) ->
                new Categorie(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql,mapper,id).stream().findFirst();
    }

    public Optional<Categorie> findByName(String name){
        String sql = "SELECT * FROM categorii WHERE Nume = ?";
        RowMapper<Categorie> mapper = ((resultSet, rowNumber) ->
                new Categorie(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql, mapper, name).stream().findFirst();
    }


    public Categorie create(Categorie categorie){
        String sql = "Insert INTO categorii VALUES(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, categorie.getNume());
            return preparedStatement;
        }, holder);

        categorie.setId(holder.getKey().intValue());
        return categorie;
    }

    public int update(Categorie categorie) {
        String sql = "UPDATE categorii SET nume = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, categorie.getNume(), categorie.getId());
        if (affectedRows == 0) {
            throw new EntityNotFoundException("Categorie");
        }
        else return affectedRows;
    }

    public String delete(int id){
        String sql = "DELETE FROM categorii WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Categorie");
        else return "Categoria a fost stearsa.";
    }
}
