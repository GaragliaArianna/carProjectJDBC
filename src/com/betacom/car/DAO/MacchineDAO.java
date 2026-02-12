package com.betacom.car.DAO;

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
