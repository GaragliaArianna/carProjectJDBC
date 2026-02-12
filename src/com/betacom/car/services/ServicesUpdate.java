package com.betacom.car.services;


import java.sql.SQLException;
import java.util.Optional;

import com.betacom.car.utils.SQLManager;
import com.betacom.car.DAO.MacchineDAO;
import com.betacom.car.DAO.MarcaDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Marca;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;

public class ServicesUpdate {

	
	private VeicoliDAO daoV=new VeicoliDAO();
	private MacchineDAO daoM = new MacchineDAO();
	private MarcaDAO daoMarca = new MarcaDAO();


	
	public void executeUpdate() throws AcademyException {
		
		try {
			
			SQLConfiguration.getInstance().setTransaction();
			int id=insertVeicolo();
			insertMacchina(id);
			SQLConfiguration.getInstance().commit();
			
			updateVeicolo(id);
			deleteVeicoloById(id);
			insertMarca("Audi");
			updateMarca(1, "Ferrari");
			deleteMarca(2);


			
		} catch (Exception e) {
			System.out.println("Errore found: "+e.getMessage());
			SQLConfiguration.getInstance().rollback();
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
	
	
	

	
	private void insertMacchina(int idVeicolo) {

	    System.out.println("*********** Insert into Macchine");

	    try {

	        Object[] params = new Object[] {
	            idVeicolo   
	        };

	        daoM.insert("update.macchine.insert", params);

	        System.out.println("Inserimento macchina OK");

	    } catch (Exception e) {
	        System.out.println("Errore insertMacchina: " + e.getMessage());
	        throw new RuntimeException(e); 
	    }
	}
	
	
	
	private void updateVeicolo(int id) {
		System.out.println("Update into Veicoli*********");
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
	
	public void deleteVeicoloById(int id) throws SQLException, AcademyException {

		SQLConfiguration.getInstance().setTransaction();

		try {

		    daoV.deleteById(id);

		    SQLConfiguration.getInstance().commit();

		} catch (Exception e) {

		    SQLConfiguration.getInstance().rollback();
		}

	}
	public void insertMarca(String nomeMarca) {
        try {
            Marca m = new Marca(null, nomeMarca); // usa il costruttore con id=null
            int result = daoMarca.insert(m);
            System.out.println("Insert completata, righe inserite: " + result);
        } catch (AcademyException e) {
            System.out.println("Error during insertMarca: " + e.getMessage());
        }
    }

    public void updateMarca(Integer idMarca, String nuovoNome) {
        try {
            Marca m = new Marca(idMarca, nuovoNome);
            int result = daoMarca.update(m);
            System.out.println("Update completato, righe modificate: " + result);
        } catch (AcademyException e) {
            System.out.println("Error during updateMarca: " + e.getMessage());
        }
    }

    public void deleteMarca(Integer idMarca) {
        try {
            int result = daoMarca.delete(idMarca);
            System.out.println("Delete completato, righe cancellate: " + result);
        } catch (AcademyException e) {
            System.out.println("Error during deleteMarca: " + e.getMessage());
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
