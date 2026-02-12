package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Colore;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ColoriDAO {

    private SQLManager db = new SQLManager();

    public List<Colore> findAll() throws Exception {

        String query = SQLConfiguration.getInstance().getQuery("query.colori");
        System.out.println(query);

        List<Map<String, Object>> lC = db.list(query);

        return resultToObject(lC);
    }
    
    public Optional<Colore> findById(Integer idColore) throws Exception {

        String query = SQLConfiguration.getInstance().getQuery("query.colori.byId");

        List<Map<String, Object>> result = db.list(query, new Object[]{idColore});

        List<Colore> colori = resultToObject(result);

        if (colori.isEmpty())
            return Optional.empty();

        
        return Optional.of(colori.get(0));
    }

    private List<Colore> resultToObject(List<Map<String, Object>> lC) {

        return lC.stream()
                .map(row -> new Colore(
                        ((Number) row.get("id_colore")).intValue(),
                        (String) row.get("colore")
                ))
                .collect(Collectors.toList());
    }

    public int insert(String qryName, Colore c) throws Exception {

        Object[] params = new Object[] {
                c.getColore()
        };

        String query = SQLConfiguration.getInstance().getQuery(qryName);
        System.out.println(query);

        return db.save(query, params, true);
    }

    public int update(String qryName, Colore c) throws Exception {

        Object[] params = new Object[] {
                c.getColore(),
                c.getIdColore()
        };

        String query = SQLConfiguration.getInstance().getQuery(qryName);
        System.out.println(query);

        return db.save(query, params);
    }

    public void deleteById(Integer idColore) throws Exception {

        String query = SQLConfiguration.getInstance()
                         .getQuery("delete.colori");

        Object[] params = new Object[] { idColore };

        System.out.println(query);

        int rows = db.delete(query, params);

        if (rows == 0) {
            throw new AcademyException(
                "Nessun colore trovato con id: " + idColore
            );
        }

        System.out.println("Colore eliminato correttamente. ID: " + idColore);
    }
}
