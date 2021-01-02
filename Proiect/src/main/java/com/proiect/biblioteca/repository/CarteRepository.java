package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CarteRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Carte> findAll(){
        String sql = "SELECT * FROM carti";
        RowMapper<Carte> mapper = ((resultSet, rowNumber) ->
                new Carte(resultSet.getInt("id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("nume"),
                        resultSet.getInt("idAutor"),
                        resultSet.getDate("dataAdaugare"),
                        resultSet.getInt("idCategorie"),
                        resultSet.getInt("stoc")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }
}

