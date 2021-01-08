package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SolicitareRepository {
    private final JdbcTemplate jdbcTemplate;

    public SolicitareRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Solicitare> findAll(){
        String sql = "SELECT * FROM solicitari";

        RowMapper<Solicitare> mapper = ((resultSet, rowNumber) ->
                new Solicitare(resultSet.getInt("id"),
                        resultSet.getInt("idImprumut"),
                        resultSet.getDate("termenAmanare"),
                        resultSet.getBoolean("aprobat")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }
}