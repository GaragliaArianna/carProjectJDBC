//package com.betacom.car.services;
//
//import java.util.Optional;
//
//import com.betacom.car.utils.SQLManager;
//import com.betacom.dao.ClientiDAO;
//import com.betacom.car.exception.AcademyException;
//import com.betacom.models.Clienti;
//import com.betacom.car.singletone.SQLConfiguration;
//
//public class ServicesUpdate {
//
//	//private SQLManager db= new SQLManager();
//	private ClientiDAO dao=new ClientiDAO();
//	
//	public void executeUpdate() {
//		
//		try {
//			SQLConfiguration.getInstance().setAutoCommit();  //autocommit is setted
//			int id=insertCliente();
//			updateCliente(id);
//			delete(id);
//		} catch (Exception e) {
//			System.out.println("Errore found: "+e.getMessage());
//		}
//	}
//	
//	private int insertCliente() {
//		System.out.println("*********** Insert into clienti");
//		int num=7;
//		
//		Clienti cli = new Clienti ("Insert by java",
//				"piva01",
//				"via test 1",
//				"22558555"	);
//	
//		
//		try {
//			 num=dao.insert("update.clienti.insert", cli);
//			 System.out.println("Inserimento cliente: "+num);
//		} catch (Exception e) {
//			System.out.println("Errore found: "+e.getMessage());
//		}
//		
//		return num;
//
//	}
//	/*
//	private void updateCliente(int id) {
//		System.out.println("Insert into clienti*********");
//		//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
//		Clienti cli=new Clienti();
//		cli.setId_cliente(id);
//		cli.setIndirizzo("Indirizzo modificato");
//		cli.setTelefono("modificato");
//		cli.setpIva("mfniefjieato");
//		
//		try {
//			dao.update(cli);
//		} catch (Exception e) {
//			System.out.println("Errore found: "+e.getMessage());
//		}
//	}*/
//	private void updateCliente(int id) {
//		System.out.println("Insert into clienti*********");
//		//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
//		Clienti cli=new Clienti();
//		cli.setId_cliente(id);
//		cli.setIndirizzo("Indirizzo modificato");
//
//		
//		try {
//			System.out.println("numero di righe modificate: "+dao.update(cli));
//			Optional <Clienti> row=dao.findById(id);
//			if(row.isPresent()) {
//				System.out.println(row.get());
//			}
//		} catch (Exception e) {
//			System.out.println("Errore found: "+e.getMessage());
//		}
//	}
//	
//	private void delete(int id) {
//		System.out.println("delete cliente **********"+id);
//		
//		try {
//			System.out.println("Numero di righe cancellate "+ dao.delete(id));
//		} catch (Exception e) {
//			System.out.println("Errore found: "+e.getMessage());
//		}
//	}
//}
