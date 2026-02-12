package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Sospensione;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class SospensioniDAO {

    private SQLManager db = new SQLManager();

    // Trova tutte le sospensioni
    public List<Sospensione> findAll() throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.sospensioni");
        List<Map<String, Object>> res = db.list(query);

        return res.stream()
                  .map(r -> new Sospensione(
                          ((Number) r.get("id_sospensione")).intValue(),
                          (String) r.get("sospensione")  // coerente con il modello
                  ))
                  .collect(Collectors.toList());
    }

    // Trova sospensione per ID
    public Optional<Sospensione> findById(Integer idSospensione) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.sospensioni.byId");
        List<Map<String, Object>> res = db.list(query, new Object[]{idSospensione});

        List<Sospensione> list = res.stream()
                                    .map(r -> new Sospensione(
                                        ((Number) r.get("id_sospensione")).intValue(),
                                        (String) r.get("sospensione")
                                    ))
                                    .toList();

        if(list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    // Inserimento
    public int insert(String qryName, Sospensione s) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        Object[] params = new Object[]{ s.getSospensione() };  // usa getSospensione
        return db.save(query, params, true);
    }

    // Aggiornamento
    public int update(String qryName, Sospensione s) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        Object[] params = new Object[]{ s.getSospensione(), s.getIdSospensione() };
        return db.save(query, params);
    }

    // Cancellazione per ID
    public void deleteById(Integer idSospensione) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("delete.sospensioni");
        Object[] params = new Object[]{ idSospensione };
        int rows = db.delete(query, params);

        if(rows == 0) 
            throw new AcademyException("Nessuna sospensione trovata con id: " + idSospensione);

        System.out.println("Sospensione eliminata correttamente. ID: " + idSospensione);
    }
}
