package com.betacom.car.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Categoria;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class CategoriaDAO {
	
	private SQLManager db = new SQLManager();

    public List<Categoria> findAll() throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.categorie");
        List<Map<String, Object>> lC = db.list(query);
        return resultToObject(lC);
    }
    
    public Optional<Categoria> findById(Integer idCategoria) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("query.categorie.byId");
        List<Map<String, Object>> result = db.list(query, new Object[]{idCategoria});
        List<Categoria> categorie = resultToObject(result);

        if (categorie.isEmpty())
            return Optional.empty();
     
        return Optional.of(categorie.get(0));
    }
    
    private List<Categoria> resultToObject(List<Map<String, Object>> lC) {
        return lC.stream()
                .map(row -> new Categoria(
                        ((Number) row.get("id_categoria")).intValue(),
                        (String) row.get("categoria")
                ))
                .collect(Collectors.toList());
    }
    
    public int insert(String qryName, Categoria c) throws Exception {
        Object[] params = new Object[] {
                c.getCategoria()
        };
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        return db.save(query, params, true);
    }
    
    public int update(String qryName, Categoria c) throws Exception {
        Object[] params = new Object[] {
                c.getCategoria(),
                c.getIdCategoria()
        };
        String query = SQLConfiguration.getInstance().getQuery(qryName);
        return db.save(query, params);
    }
    
    public void deleteById(Integer idCategoria) throws Exception {
        String query = SQLConfiguration.getInstance().getQuery("delete.categorie");
        Object[] params = new Object[] { idCategoria };
        int rows = db.delete(query, params);

        if (rows == 0) {
            throw new AcademyException("Nessuna categoria trovata con id: " + idCategoria);
        };
    }

}