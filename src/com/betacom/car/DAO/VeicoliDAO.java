package com.betacom.car.DAO;


import java.sql.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;



public class VeicoliDAO {

    private SQLManager db = new SQLManager();

    public List<Veicoli> findAll() throws Exception {

        String query = SQLConfiguration.getInstance().getQuery("query.veicoli");
        System.out.println(query);

		List<Map<String, Object>> lV = db.list(query);

        return resultToObject(lV);
    }

    
    
	public List<Veicoli> findByTipo(String qryName, Object [] idTipoVeicolo) throws Exception {
		String query=SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println(query);
		
		List<Map<String, Object>> lV=db.list(query, idTipoVeicolo);
		
		return resultToObject(lV);
	
	}
	

   
    private List<Veicoli> resultToObject(List<Map<String, Object>> lV) {

        return lV.stream()
                .map(row -> {
              	
                    // Anno di produzione
                    Integer annoProduzione = row.get("anno_produzione") != null
                            ? ((Number) row.get("anno_produzione")).intValue()
                            : null;

                    return new Veicoli(
                            ((Number) row.get("id_veicolo")).intValue(), // id veicolo
                            (String) row.get("veicolo"),           // tipo veicolo come stringa
                            ((Number) row.get("numero_ruote")).intValue(), // numero ruote
                            (String) row.get("tipo_alimentazione"),         // alimentazione come stringa
                            (String) row.get("categoria"),             // categoria come stringa
                            (String) row.get("colore"),                // colore come stringa
                            (String) row.get("marca"),                 // marca come stringa
                            annoProduzione,
                            (String) row.get("modello")
                    );
                })
                .collect(Collectors.toList());
    }

	public int insert( String qryName, Veicoli v ) throws Exception {
		
	    Object[] params = new Object[] {
	            v.getIdTipoVeicolo(),
	            v.getNumeroRuote(),
	            v.getIdAlimentazione(),
	            v.getIdCategoria(),
	            v.getIdColore(),
	            v.getIdMarca(),
	            v.getAnnoProduzione(),
	            v.getModello()
	        };
		
		 String query= SQLConfiguration.getInstance().getQuery(qryName);
		 System.out.println(query);
		 
		 return db.save(query, params, true);
		
	}
	
	public int update(String qryName, Veicoli v) throws Exception {

	    Object[] params = new Object[] {
	            v.getIdTipoVeicolo(),
	            v.getNumeroRuote(),
	            v.getIdAlimentazione(),
	            v.getIdCategoria(),
	            v.getIdColore(),
	            v.getIdMarca(),
	            v.getAnnoProduzione(),
	            v.getModello(),
	            v.getId()
	    };

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params);
	}

	public void deleteById(Integer idVeicolo) throws Exception {

	    String query = SQLConfiguration.getInstance()
	                     .getQuery("delete.veicoli");

	    Object[] params = new Object[] { idVeicolo };

	    System.out.println(query);

	    int rows = db.delete(query, params);

	    if (rows == 0) {
	        throw new AcademyException(
	            "Nessun veicolo trovato con id: " + idVeicolo
	        );
	    }

	    System.out.println("Veicolo eliminato correttamente. ID: " + idVeicolo);
	}



}

