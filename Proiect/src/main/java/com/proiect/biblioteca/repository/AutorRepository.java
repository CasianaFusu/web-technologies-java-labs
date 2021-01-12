package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
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
public class AutorRepository {
    private final JdbcTemplate jdbcTemplate;

    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Autor> findAll(){
        String sql = "SELECT * FROM autori";
        RowMapper<Autor> mapper = ((resultSet, rowNumber) ->
                new Autor(resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }

    public Optional<Autor> findByName(String name){
        String sql = "SELECT * FROM autori WHERE Nume = ?";
        RowMapper<Autor> mapper = ((resultSet, rowNumber) ->
                new Autor(resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume")
                )
        );
        return jdbcTemplate.query(sql, mapper, name).stream().findFirst();
    }

    public Optional<Autor> findById(int id){
        String sql = "SELECT * FROM autori WHERE id = ?";

        RowMapper<Autor> mapper = ((resultSet, rowNumber) ->
                new Autor(resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume")
                )
        );

        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public Autor create(Autor autor){
        String sql = "INSERT INTO autori VALUES(?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, autor.getNume());
            preparedStatement.setObject(3, autor.getPrenume());
            return preparedStatement;
        }, holder);

        autor.setId(holder.getKey().intValue());
        return autor;
    }

    public int update(Autor autor) {
        String sql = "UPDATE autori SET nume = ?, prenume = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, autor.getNume(), autor.getPrenume(), autor.getId());
        if (affectedRows == 0) {
            throw new RuntimeException("Nu a fost gasita niciun autor cu id-ul " + autor.getId());
        }
        else return affectedRows;
    }

    public String delete(int id){
        String sql = "DELETE FROM autori WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new RuntimeException("Nu a fost gasit niciun autor cu id-ul " + id);
        else return "Autorul a fost sters.";
    }
}
