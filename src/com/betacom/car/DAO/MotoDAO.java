package com.betacom.car.DAO;

import java.util.Map;
import java.util.Optional;

import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class MotoDAO {
	
	private SQLManager db = new SQLManager();

	public Optional<Moto> findMotoByTarga(Object[] params) throws Exception {

	    String query = SQLConfiguration.getInstance().getQuery("query.moto.getByTarga");
	    Map<String, Object> row = db.get(query, params);

	    if (row == null) return Optional.empty();

	    // Leggiamo l'anno come intero
	    Integer annoProduzione = row.get("anno_produzione") != null
	            ? ((Number) row.get("anno_produzione")).intValue()
	            : null;

	    // Costruiamo la Moto con i valori testuali
	    Moto moto = new Moto(
	            ((Number) row.get("id_veicolo")).intValue(),         // id veicolo
	            (String) row.get("veicolo"),                   // stringa
	            ((Number) row.get("numero_ruote")).intValue(),      // numero ruote
	            (String) row.get("tipo_alimentazione"),                  // stringa
	            (String) row.get("categoria"),                      // stringa
	            (String) row.get("colore"),                          // stringa
	            (String) row.get("marca"),                           // stringa
	            annoProduzione,
	            (String) row.get("modello"),
	            ((Number) row.get("cilindrata")).intValue(),       // cilindrata
	            (String) row.get("targa")                           // targa
	    );

	    return Optional.of(moto);
	}
	
	public int insert(String qryName, Object[] params) throws Exception {

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params, false);
	}
	
	public int update(String qryName, Moto m) throws Exception {

	    Object[] params = new Object[] {
	            m.getCilindrata(),
	            m.getId()
	    };

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params);
	}
}
