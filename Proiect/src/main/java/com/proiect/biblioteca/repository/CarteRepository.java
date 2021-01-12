package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.exception.EntityNotFoundException;
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

    public Optional<Carte> findById(int id){
        String sql = "SELECT * FROM carti WHERE id = ?";
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
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }


    public Optional<Carte> findByName(String name){
        String sql = "SELECT * FROM carti WHERE Nume = ?";
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
        return jdbcTemplate.query(sql, mapper, name).stream().findFirst();
    }

    public List<Carte> findByIdCategorie(int id){
        String sql = "SELECT * FROM carti WHERE idCategorie = ?";
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
        return jdbcTemplate.query(sql, mapper, id);
    }

    public Carte create(Carte carte){
        String sql = "Insert INTO carti VALUES(?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, carte.getIsbn());
            preparedStatement.setObject(3, carte.getNume());
            preparedStatement.setObject(4, carte.getIdAutor());
            preparedStatement.setObject(5, carte.getDataAdaugare());
            preparedStatement.setObject(6, carte.getIdCategorie());
            preparedStatement.setObject(7, carte.getStoc());
            return preparedStatement;
        }, holder);

        carte.setId(holder.getKey().intValue());
        return carte;
    }

    public int update(Carte carte) {
        String sql = "UPDATE carti SET isbn = ?, nume = ?, idAutor = ?, dataAdaugare = ?, idCategorie = ?, stoc =? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, carte.getIsbn(), carte.getNume(), carte.getIdAutor(), carte.getDataAdaugare(),
                carte.getIdCategorie(),carte.getStoc(), carte.getId());
        if (affectedRows == 0) {
            throw new RuntimeException("Nu a fost gasita nicio carte cu id-ul " + carte.getId());
        }
        else return affectedRows;
    }

    public String delete(int id){
        String sql = "DELETE FROM carti WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Carte");
        else return "Cartea a fost stearsa.";
    }

    public List<Carte> findByIdAutor(int idAutor){
        String sql = "SELECT * FROM carti WHERE idAutor = ?";

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
        return jdbcTemplate.query(sql,mapper, idAutor);
    }

    public int decreaseStocById(int id){
        String sql = "UPDATE carti SET stoc = stoc - 1 WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Carte");
        else return affectedRows;
    }

    public int increaseStocById(int id){
        String sql = "UPDATE carti SET stoc = stoc + 1 WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new RuntimeException("Nu a fost gasita nicio carte cu id-ul " + id);
        else return affectedRows;
    }
}

