package com.betacom.car.services;


import java.util.Optional;

import com.betacom.car.utils.SQLManager;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;

public class ServicesUpdate {

	
	private VeicoliDAO daoV=new VeicoliDAO();
	
	public void executeUpdate() {
		
		try {
			//SQLConfiguration.getInstance().setAutoCommit(false);  //false perchè devo fare 2 insert collegate
			int id=insertVeicolo();
			updateVeicolo(id);
			//delete(id);
		} catch (Exception e) {
			System.out.println("Errore found: "+e.getMessage());
		}
	}
	
	private int insertVeicolo() {

	    System.out.println("*********** Insert into Veicoli ************");

	    int idGenerato = 0;

	    Veicoli v = new Veicoli();

	    // ID relazioni
	    v.setIdTipoVeicolo(1);     // 1 = Macchina
	    v.setNumeroRuote(4);
	    v.setIdAlimentazione(2);   // esempio Diesel
	    v.setIdCategoria(3);
	    v.setIdColore(2);
	    v.setIdMarca(1);

	    // dati comuni
	    v.setAnnoProduzione(2010);
	    v.setModello("Punto");

	    try {
	        idGenerato = daoV.insert("update.veicoli.insert", v);
	        System.out.println("Inserimento veicolo OK. ID generato: " + idGenerato);

	    } catch (Exception e) {
	        System.out.println("Errore found: " + e.getMessage());
	    }

	    return idGenerato;
	}
	
	
	

	
	private void insertMacchina (int id) {
		
	}
	
	
	
	private void updateVeicolo(int id) {
		System.out.println("Update into clienti*********");
		//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
		Veicoli vei=new Veicoli();
	     vei.setId(id);

	        // --- NUOVI VALORI ---
	        vei.setIdTipoVeicolo(1);     // Macchina
	        vei.setNumeroRuote(4);
	        vei.setIdAlimentazione(1);   // Benzina
	        vei.setIdCategoria(2);       // Sportiva
	        vei.setIdColore(1);          // Rosso
	        vei.setIdMarca(1);           // Fiat
	        vei.setAnnoProduzione(2020);
	        vei.setModello("Punto EVO");

	        int righe;
			try {
				righe = daoV.update("update.veicoli.update", vei);
		        System.out.println("Righe aggiornate: " + righe);

			} catch (Exception e) {
				
		        System.out.println("Errore found: " + e.getMessage());
			}


	    } 
	

	
	/*
	private void updateCliente(int id) {
		System.out.println("Insert into clienti*********");
		//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
		Clienti cli=new Clienti();
		cli.setId_cliente(id);
		cli.setIndirizzo("Indirizzo modificato");
		cli.setTelefono("modificato");
		cli.setpIva("mfniefjieato");
		
		try {
			dao.update(cli);
		} catch (Exception e) {
			System.out.println("Errore found: "+e.getMessage());
		}
	}*/
	
	/*
	private void updateCliente(int id) {
		System.out.println("Insert into clienti*********");
		//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
		Clienti cli=new Clienti();
		cli.setId_cliente(id);
		cli.setIndirizzo("Indirizzo modificato");

		
		try {
			System.out.println("numero di righe modificate: "+dao.update(cli));
			Optional <Clienti> row=dao.findById(id);
			if(row.isPresent()) {
				System.out.println(row.get());
			}
		} catch (Exception e) {
			System.out.println("Errore found: "+e.getMessage());
		}
	}
	
	private void delete(int id) {
		System.out.println("delete cliente **********"+id);
		
		try {
			System.out.println("Numero di righe cancellate "+ dao.delete(id));
		} catch (Exception e) {
			System.out.println("Errore found: "+e.getMessage());
		}
	}*/
}
