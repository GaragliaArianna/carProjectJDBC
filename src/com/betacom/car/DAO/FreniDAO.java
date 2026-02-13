package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.models.Freno;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;
import com.betacom.car.exception.AcademyException;

public class FreniDAO {

    protected final SQLManager db = new SQLManager();

    protected String getQuery(String key) throws AcademyException {
        return SQLConfiguration.getInstance().getQuery(key);
    }

    // SELECT *
    public List<Freno> findAll() throws AcademyException {
        String q = getQuery("query.freno");
        System.out.println("QUERY FRENO: " + q);
        return db.list(q)
                 .stream()
                 .map(this::toObject)
                 .collect(Collectors.toList());
    }


    // SELECT by ID
    public Optional<Freno> findById(Integer id) throws AcademyException {
        Map<String, Object> row = db.get(getQuery("query.freno.byId"), new Object[]{id});
        return row == null ? Optional.empty() : Optional.of(toObject(row));
    }

    // INSERT
    public int insert(Freno f) throws AcademyException {
        return db.save(getQuery("insert.freno"), new Object[]{f.getFreno()}, true);
    }

    // UPDATE
    public int update(Freno f) throws AcademyException {
        return db.save(getQuery("update.freno"), new Object[]{f.getFreno(), f.getIdFreno()});
    }

    // DELETE
    public int delete(Integer id) throws AcademyException {
        return db.save(getQuery("delete.freno"), new Object[]{id});
    }

    private Freno toObject(Map<String, Object> row) {
        return new Freno(
            (Integer) row.get("id_freno"),
            (String) row.get("freno")
        );
    }
}
