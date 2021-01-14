package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
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
public class RolRepository {
    private final JdbcTemplate jdbcTemplate;

    public RolRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rol> findAll(){
        String sql = "SELECT * FROM roluri";

        RowMapper<Rol> mapper = ((resultSet, rowNumber) ->
                new Rol(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }

    public Optional<Rol> findById(int id){
        String sql = "SELECT * FROM roluri WHERE id = ?";

        RowMapper<Rol> mapper = ((resultSet, rowNumber) ->
                new Rol(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql,mapper,id).stream().findFirst();
    }

    public Optional<Rol> findByName(String name){
        String sql = "SELECT * FROM roluri WHERE Nume = ?";
        RowMapper<Rol> mapper = ((resultSet, rowNumber) ->
                new Rol(resultSet.getInt("id"),
                        resultSet.getString("nume")
                )
        );
        return jdbcTemplate.query(sql, mapper, name).stream().findFirst();
    }


    public Rol create(Rol rol){
        String sql = "Insert INTO roluri VALUES(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, rol.getNume());
            return preparedStatement;
        }, holder);

        rol.setId(holder.getKey().intValue());
        return rol;
    }

    public int update(Rol rol) {
        String sql = "UPDATE roluri SET nume = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, rol.getNume(), rol.getId());
        if (affectedRows == 0) {
            throw new EntityNotFoundException("Rol");
        }
        else return affectedRows;
    }

    public String delete(int id){
        String sql = "DELETE FROM roluri WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Rol");
        else return "Rolul a fost sters.";
    }
}
