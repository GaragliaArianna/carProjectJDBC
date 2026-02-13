package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Alimentazione;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class AlimentazioniDAO {

    private SQLManager db = new SQLManager();

    // ===== FIND ALL =====
    public List<Alimentazione> findAll() throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.alimentazioni");
        List<Map<String, Object>> res = db.list(query);

        return res.stream()
                .map(r -> new Alimentazione(
                        ((Number) r.get("id_alimentazione")).intValue(),
                        (String) r.get("tipo_alimentazione")))
                .collect(Collectors.toList());
    }

    // ===== FIND BY ID =====
    public Optional<Alimentazione> findById(Integer idAlimentazione) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.alimentazioni.byId");
        List<Map<String, Object>> res = db.list(query, new Object[]{idAlimentazione});

        List<Alimentazione> list = res.stream()
                .map(r -> new Alimentazione(
                        ((Number) r.get("id_alimentazione")).intValue(),
                        (String) r.get("tipo_alimentazione")))
                .toList();

        if (list.isEmpty())
            return Optional.empty();

        return Optional.of(list.get(0));
    }

    // ===== INSERT =====
    public int insert(String qryName, Alimentazione a) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        Object[] params = new Object[]{a.getTipoAlimentazione()};
        return db.save(query, params, true);
    }

    // ===== UPDATE =====
    public int update(String qryName, Alimentazione a) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        Object[] params = new Object[]{a.getTipoAlimentazione(), a.getIdAlimentazione()};
        return db.save(query, params);
    }

    // ===== DELETE =====
    public void deleteById(Integer idAlimentazione) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("delete.alimentazioni");
        Object[] params = new Object[]{idAlimentazione};

        int rows = db.delete(query, params);

        if (rows == 0)
            throw new AcademyException("Nessuna alimentazione trovata con id: " + idAlimentazione);

        System.out.println("Alimentazione eliminata correttamente. ID: " + idAlimentazione);
    }
}
