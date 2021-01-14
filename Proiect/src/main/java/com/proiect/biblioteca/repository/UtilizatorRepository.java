package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Utilizator;
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
public class UtilizatorRepository {
    private final JdbcTemplate jdbcTemplate;

    public UtilizatorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Utilizator> findAll(){
        String sql = "SELECT * FROM utilizatori";
        RowMapper<Utilizator> mapper = ((resultSet, rowNumber) ->
                new Utilizator(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getBoolean("activat"),
                        resultSet.getInt("idRol")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }

    public List<Utilizator> findAllByRol(int idRol){
        String sql = "SELECT * FROM utilizatori where idRol = ?";
        RowMapper<Utilizator> mapper = ((resultSet, rowNumber) ->
                new Utilizator(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getBoolean("activat"),
                        resultSet.getInt("idRol")
                )
        );
        return jdbcTemplate.query(sql,mapper,idRol);
    }

    public Optional<Utilizator> findById(int id){
        String sql = "SELECT * FROM utilizatori WHERE id = ?";
        RowMapper<Utilizator> mapper = ((resultSet, rowNumber) ->
                new Utilizator(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getBoolean("activat"),
                        resultSet.getInt("idRol")
                )
        );
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public Utilizator create(Utilizator utilizator){
        String sql = "Insert INTO utilizatori VALUES(?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, utilizator.getUsername());
            preparedStatement.setObject(3, utilizator.getNume());
            preparedStatement.setObject(4, utilizator.getPrenume());
            preparedStatement.setObject(5, utilizator.getEmail());
            preparedStatement.setObject(6, utilizator.getParola());
            preparedStatement.setObject(7, utilizator.isActivat());
            preparedStatement.setObject(8, utilizator.getIdRol());
            return preparedStatement;
        }, holder);

        utilizator.setId(holder.getKey().intValue());
        return utilizator;
    }

    public int update(Utilizator utilizator) {
        String sql = "UPDATE utilizatori SET username = ?, nume = ?, prenume = ?, email = ?, parola = ?, idRol = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, utilizator.getUsername(), utilizator.getNume(), utilizator.getPrenume(), utilizator.getEmail(),utilizator.getParola(), utilizator.getIdRol(), utilizator.getId());
        if (affectedRows == 0) {
            throw new EntityNotFoundException("Utilizator");
        }
        else return affectedRows;
    }

    public int activate(int id) {
        String sql = "UPDATE utilizatori SET activat = 1 WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        if (affectedRows == 0){
            throw new EntityNotFoundException("Utilizator");
        }
        else return affectedRows;
    }

    public Optional<Utilizator> findByUsername(String username){
        String sql = "SELECT * FROM utilizatori WHERE username = ?";
        RowMapper<Utilizator> mapper = ((resultSet, rowNumber) ->
                new Utilizator(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getBoolean("activat"),
                        resultSet.getInt("idRol")
                )
        );
        return jdbcTemplate.query(sql, mapper, username).stream().findFirst();
    }

    public String delete(int id){
        String sql = "DELETE FROM utilizatori WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Utilizator");
        else return "Utilizatorul a fost sters.";
    }
}
