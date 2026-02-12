package com.betacom.car;


import com.betacom.car.services.ServicesQuery;
import com.betacom.car.services.ServicesUpdate;
import com.betacom.car.singletone.SQLConfiguration;

public class MainCar {

	public static void main(String[] args) {
		
			
			try {
				SQLConfiguration.getInstance().getConnection();
				System.out.println("Connection with DB OK");
				
				new ServicesQuery().executeQuery();
				new ServicesUpdate().executeUpdate();
//				new ServicesTransaction().executeTransaction();
				SQLConfiguration.getInstance().closeConnection();
	
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			

		
	}

}
