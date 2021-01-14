package com.proiect.biblioteca.repository;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ImprumutRepository {
    private final JdbcTemplate jdbcTemplate;

    public ImprumutRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Imprumut> findAll(){
        String sql = "SELECT * FROM imprumuturi";

        RowMapper<Imprumut> mapper = ((resultSet, rowNumber) ->
                new Imprumut(resultSet.getInt("id"),
                        resultSet.getInt("idUtilizator"),
                        resultSet.getInt("idCarte"),
                        resultSet.getDate("dataImprumut"),
                        resultSet.getDate("dataExpirare"),
                        resultSet.getBoolean("incheiat")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }
    public List<Imprumut> findAllNeincheiate(){
        String sql = "SELECT * FROM imprumuturi WHERE incheiat = false";

        RowMapper<Imprumut> mapper = ((resultSet, rowNumber) ->
                new Imprumut(resultSet.getInt("id"),
                        resultSet.getInt("idUtilizator"),
                        resultSet.getInt("idCarte"),
                        resultSet.getDate("dataImprumut"),
                        resultSet.getDate("dataExpirare"),
                        resultSet.getBoolean("incheiat")
                )
        );
        return jdbcTemplate.query(sql,mapper);
    }


    public List<Imprumut> findAllByIdUtilizator(int id){
        String sql = "SELECT * FROM imprumuturi WHERE idUtilizator = ? AND incheiat = true";

        RowMapper<Imprumut> mapper = ((resultSet, rowNumber) ->
                new Imprumut(resultSet.getInt("id"),
                        resultSet.getInt("idUtilizator"),
                        resultSet.getInt("idCarte"),
                        resultSet.getDate("dataImprumut"),
                        resultSet.getDate("dataExpirare"),
                        resultSet.getBoolean("incheiat")
                )
        );
        return jdbcTemplate.query(sql,mapper,id);
    }

    public Imprumut create(Imprumut imprumut){
        String sql = "Insert INTO imprumuturi VALUES(?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,null);
            preparedStatement.setObject(2, imprumut.getIdUtilizator());
            preparedStatement.setObject(3, imprumut.getIdCarte());
            preparedStatement.setObject(4, imprumut.getDataImprumut());
            preparedStatement.setObject(5, imprumut.getDataExpirare());
            preparedStatement.setObject(6, 0);
            return preparedStatement;
        }, holder);

        imprumut.setId(holder.getKey().intValue());
        return imprumut;
    }

    public Optional<Imprumut> findById(int id){
        String sql = "SELECT * FROM imprumuturi WHERE id = ?";

        RowMapper<Imprumut> mapper = ((resultSet, rowNumber) ->
                new Imprumut(resultSet.getInt("id"),
                        resultSet.getInt("idUtilizator"),
                        resultSet.getInt("idCarte"),
                        resultSet.getDate("dataImprumut"),
                        resultSet.getDate("dataExpirare"),
                        resultSet.getBoolean("incheiat")
                )
        );
        return jdbcTemplate.query(sql,mapper,id).stream().findFirst();
    }

    public List<Imprumut> findByIdCarte(int idCarte){
        String sql = "SELECT * FROM imprumuturi WHERE idCarte = ?";

        RowMapper<Imprumut> mapper = ((resultSet, rowNumber) ->
                new Imprumut(resultSet.getInt("id"),
                        resultSet.getInt("idUtilizator"),
                        resultSet.getInt("idCarte"),
                        resultSet.getDate("dataImprumut"),
                        resultSet.getDate("dataExpirare"),
                        resultSet.getBoolean("incheiat")
                )
        );
        return jdbcTemplate.query(sql,mapper, idCarte);
    }

    public int changeTermenExpirareImprumut(int id, Date data){
        String sql = "UPDATE imprumuturi SET  dataExpirare = ? WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, data, id);
        if (affectedRows == 0) {
            throw new EntityNotFoundException("Imprumut");
        }
        else return affectedRows;
    }

    public String delete(int id){
        String sql = "DELETE FROM imprumuturi WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);
        if(affectedRows == 0)
            throw new EntityNotFoundException("Imprumut");
        else return "Imprumutul a fost sters.";
    }

    public int update(Imprumut imprumut) {
        String sql = "UPDATE imprumuturi SET idCarte = ?, dataImprumut = ?, dataExpirare = ?, incheiat = ?  WHERE id = ?";

        int affectedRows = jdbcTemplate.update(sql, imprumut.getIdCarte(), imprumut.getDataImprumut(), imprumut.getDataExpirare(), imprumut.isIncheiat(), imprumut.getId());
        if (affectedRows == 0) {
            throw new RuntimeException("Nu a fost gasita nicio categorie cu id-ul " + imprumut.getId());
        }
        else return affectedRows;
    }
}
