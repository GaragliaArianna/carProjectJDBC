package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.models.Marca;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;
import com.betacom.car.exception.AcademyException; // assicurati che sia questo il package

public class MarcaDAO {

    protected final SQLManager db = new SQLManager();

    protected String getQuery(String key) throws AcademyException {
        return SQLConfiguration.getInstance().getQuery(key);
    }

    public List<Marca> findAll() throws AcademyException {
        return db.list(getQuery("query.marca"))
                 .stream()
                 .map(this::toObject)
                 .collect(Collectors.toList());
    }

    public Optional<Marca> findById(Integer id) throws AcademyException {
        Map<String, Object> row = db.get(getQuery("query.marca.byId"), new Object[]{id});
        return row == null ? Optional.empty() : Optional.of(toObject(row));
    }

    public int insert(Marca m) throws AcademyException {
        return db.save(getQuery("insert.marca"), new Object[]{m.getMarca()}, true);
    }

    public int update(Marca m) throws AcademyException {
        return db.save(getQuery("update.marca"), new Object[]{m.getMarca(), m.getIdMarca()});
    }

    public int delete(Integer id) throws AcademyException {
        return db.save(getQuery("delete.marca"), new Object[]{id});
    }

    private Marca toObject(Map<String, Object> row) {
        return new Marca(
            (Integer) row.get("id_marca"),
            (String) row.get("marca")
        );
    }
}
