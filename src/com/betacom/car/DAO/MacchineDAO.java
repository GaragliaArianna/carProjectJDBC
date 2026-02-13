package com.betacom.car.DAO;

import java.util.Map;
import java.util.Optional;

import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;


public class MacchineDAO {
	
	private SQLManager db= new SQLManager();
	
	public int insert(String qryName, Object[] params) throws Exception {

	    String query = SQLConfiguration.getInstance().getQuery(qryName);
	    System.out.println(query);

	    return db.save(query, params, false);
	}

	


}