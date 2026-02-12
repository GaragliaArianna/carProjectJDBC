//package com.betacom.car.services;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import com.betacom.car.utils.SQLManager;
//import com.betacom.dao.ClientiDAO;
//import com.betacom.dao.DipendentiDAO;
//import com.betacom.dao.RapportoClientiDAO;
//import com.betacom.car.exception.AcademyException;
//import com.betacom.models.Clienti;
//import com.betacom.models.Dipendenti;
//import com.betacom.models.RapportoCliente;
//import com.betacom.car.singletone.SQLConfiguration;
//
//public class ServicesTransaction {
//	
//	private final DipendentiDAO daoD;
//	private final ClientiDAO daoC;
//	private static final int DIPENDENTE_ID=16;
//	private final RapportoClientiDAO daoR;
//	private final SQLManager db;
//
//	public ServicesTransaction() {
//		this.daoD = new DipendentiDAO();
//		this.daoC = new ClientiDAO();
//		this.daoR=new RapportoClientiDAO();
//		this.db=new SQLManager();
//	}
//	
//	public void executeTransaction() {
//		System.out.println("Begin transaction");
//		try {
//			SQLConfiguration.getInstance().setTransaction();
//			int idCliente=createCliente();
//			
//			getDipendente(DIPENDENTE_ID);
//			createRapportoCliente(idCliente);
//			findAllRapporti();
//			
//			generateError();
//			
//			db.commit();	
//			
//		} catch (Exception e) {
//			System.out.println("Errore trovato: "+e.getMessage());
//			try {
//				db.rollback();
//			} catch (AcademyException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	private void getDipendente(int id) throws Exception{
//		System.out.println("*********** get dipendente "+ id);
//		
//		Optional<Dipendenti> dip=daoD.findById(id);
//		if (dip.isEmpty()) throw new AcademyException("Dipendente non trovato: "+id);
//		
//		System.out.println(dip);
//		
//	}
//	
//	private int createCliente() throws Exception {
//		System.out.println("*********** Insert into clienti");
//		int num=0;
//		
//		Clienti cli = new Clienti ("Cliente transazione",
//				"pivatrans",
//				"via transazione 1",
//				"transazi"	);
//
//			 num=daoC.insert("update.clienti.insert", cli);
//			 System.out.println("Inserimento cliente: "+num);
//			 
//			 Optional <Clienti> c=daoC.findById(num);
//			 
//			 if (c.isEmpty()) throw new AcademyException("CLiente non trovato: "+num);
//			 
//			 System.out.println(c.get());
//	
//		
//		return num;
//		
//	}
//	
//	private void createRapportoCliente(int idCliente) throws Exception {
//		System.out.println("*********** Insert rapporto-clienti");
//		
//		RapportoCliente rap= new RapportoCliente("descrizionetest", idCliente, DIPENDENTE_ID);
//		int idRapporto=daoR.insert(rap);
//		System.out.println("Rapporto cliente creato: "+idRapporto);
//		
//		Optional<RapportoCliente> row=daoR.findById(idRapporto);
//		//c'è errore
//		if (row.isEmpty()) throw new AcademyException("Rapporto non trovato");
//		
//		System.out.println(row.get());
//		
//		
//	}
//
//	private void findAllRapporti () throws Exception {
//		System.out.println("Insert into findAll Rapporti********");
//		List<RapportoCliente> lR=daoR.findAll();
//		lR.forEach(r -> System.out.println(r));
//	}

//	
//	private void generateError() throws Exception {
//		throw new AcademyException("Error generated...");
//	}
//
//}
