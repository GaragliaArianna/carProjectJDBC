package com.betacom.car.DAO;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Bici;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;


public class BicicletteDAO {
	
private SQLManager db= new SQLManager();
	// INSERT BICI
	public int insert(String qryName, Object[] params) throws Exception {
	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);
	    return db.save(query, params, false);
	}
	//DELETE BICI BY ID BICI
	public void deleteById(Integer idBicicletta) throws Exception {
	    String query = SQLConfiguration.getInstance().getQuery("delete.bici");
	    Object[] params = new Object[] { idBicicletta };
	    System.out.println(query);
	    int rows = db.delete(query, params);
	    if (rows == 0) {
	        throw new AcademyException(
	            "Nessuna Bici trovata con id: " + idBicicletta
	        );
	    }
	    System.out.println("Bici eliminata correttamente. ID: " + idBicicletta);
	}
	//UPDATE BICI
	public int update(String qryName, Bici b) throws Exception{
		    Object[] params = new Object[] {
		            b.getNumeroMarce(),
		            b.getIdFreno(),
		            b.getIdSospensione(),
		            b.getId()
		    };
		    String query = SQLConfiguration.getInstance().getQuery(qryName);
		    System.out.println(query);
		    return db.save(query, params);
	}

}

