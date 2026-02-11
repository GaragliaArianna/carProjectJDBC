package com.betacom.car;

import java.util.ArrayList;
import java.util.List;

import com.betacom.car.services.ServicesQuery;
//import com.betacom.car.services.ServicesTransaction;
import com.betacom.car.singletone.ListManager;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.singletone.SQLConfiguration;


public class MainCar {

	public static void main(String[] args) {
		
		/*
		List<String> params = new ArrayList<String>();
		
		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2025,modello=500,porte=4,targa=el234gx,cc=1200");
		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2026,modello=panda,porte=4,targa=fl234gx,cc=1300");
		params.add("delete;macchina;id=2");
		params.add("add;moto;ruote=2,alim=benzina,cat=strada,colore=nero,marca=Yamaha,anno=2025,modello=r1,targa=EL22239,cc=900");
		params.add("add;bici;ruote=2,alim=manuale,cat=strada,colore=nero,marca=Bianchi,anno=2025,modello=Grizl 5,marce=10,sospenzione=senza,piegevole=no");
		params.add("list;type=all");
		params.add("list;type=filter,tipoVeicolo=macchina");
		
		try {
			ListManager.getInstance().loadConstant();
			new StartCar().execute(params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		
		
			
			try {
				SQLConfiguration.getInstance().getConnection();
				System.out.println("Connection with DB OK");
				
				new ServicesQuery().executeQuery();
				//new ServicesUpdate().executeUpdate();
//				new ServicesTransaction().executeTransaction();
//				SQLConfiguration.getInstance().closeConnection();
//		
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			

		
	}

}
