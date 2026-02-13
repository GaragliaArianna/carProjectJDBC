package com.betacom.car.DAO;

import java.util.Map;
import java.util.Optional;

import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;


public class MacchineDAO {
	
	private SQLManager db= new SQLManager();
	
	public int insert(String qryName, Object[] params) throws Exception {

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params, false);
	}

	public Optional<Macchina> findMacchinaByTarga(Object[] params) throws Exception {

	    String query = SQLConfiguration.getInstance().getQuery("query.macchina.getByTarga");
	    Map<String, Object> row = db.get(query, params);

	    if (row == null) return Optional.empty();

	    // Leggiamo l'anno come intero
	    Integer annoProduzione = row.get("anno_produzione") != null
	            ? ((Number) row.get("anno_produzione")).intValue()
	            : null;

	    // Costruiamo la Macchina con i valori testuali
	    Macchina macchina = new Macchina(
	            ((Number) row.get("id_veicolo")).intValue(),         // id veicolo
	            (String) row.get("veicolo"),                   // stringa
	            ((Number) row.get("numero_ruote")).intValue(),      // numero ruote
	            (String) row.get("tipo_alimentazione"),                  // stringa
	            (String) row.get("categoria"),                      // stringa
	            (String) row.get("colore"),                          // stringa
	            (String) row.get("marca"),                           // stringa
	            annoProduzione,
	            (String) row.get("modello"),
	            ((Number) row.get("porte")).intValue(),
	            (String) row.get("targa"),                         // targa,       // cilindrata
	            ((Number) row.get("cilindrata")).intValue()
	    );

	    return Optional.of(macchina);
	}


	public int update(String qryName, Macchina m) throws Exception {

	    Object[] params = new Object[] {
	            m.getPorte(),
	            m.getCilindrata(),
	            m.getId()
	    };

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params);
	}
}
