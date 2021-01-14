package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
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


    public Optional<Solicitare> findById(int id){
        String sql = "SELECT * FROM solicitari where id=?";

        RowMapper<Solicitare> mapper = ((resultSet, rowNumber) ->
                new Solicitare(resultSet.getInt("id"),
                        resultSet.getInt("idImprumut"),
                        resultSet.getDate("termenAmanare"),
                        resultSet.getBoolean("aprobat")
                )
        );
        return jdbcTemplate.query(sql,mapper,id).stream().findFirst();
    }

    public Solicitare create(Solicitare solicitare){
        String sql = "Insert INTO solicitari VALUES(?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, solicitare.getIdImprumut());
            preparedStatement.setObject(3, solicitare.getTermenAmanare());
            preparedStatement.setObject(4, false);
            return preparedStatement;
        }, holder);

        solicitare.setId(holder.getKey().intValue());
        return solicitare;
    }

    public Solicitare aproba(int id) {
        String sql = "UPDATE solicitari SET aprobat = true WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        if (affectedRows == 0) {
            throw new EntityNotFoundException("Imprumut");
        }
        var modifiedSolicitare = findById(id);
        return modifiedSolicitare.get();
    }
}

