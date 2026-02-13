package com.betacom.car.services;

import java.sql.SQLException;

import java.util.Optional;

import com.betacom.car.utils.SQLManager;
import com.betacom.car.DAO.BicicletteDAO;
import com.betacom.car.DAO.ColoriDAO;
import com.betacom.car.DAO.MacchineDAO;
import com.betacom.car.DAO.MarcaDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Marca;

import com.betacom.car.DAO.MotoDAO;

import com.betacom.car.DAO.SospensioniDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Sospensione;

import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Marca;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;

public class ServicesUpdate {

	
	private VeicoliDAO daoV=new VeicoliDAO();
	private MacchineDAO daoM = new MacchineDAO();
	private BicicletteDAO daoB = new BicicletteDAO();
	private MotoDAO daoMO = new MotoDAO();

	private ColoriDAO daoC= new ColoriDAO ();
	private SospensioniDAO daoS = new SospensioniDAO();


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


			 int idColore = insertColore("Blu2");          
		     updateColore(idColore, "Blu Elettrico2");    
		     deleteColore(idColore);   
		        
		        
		     int idSosp = insertSospensione("Ammortizzatore Standard");
		     updateSospensione(idSosp, "Ammortizzatore Sportivo");
		     deleteSospensione(idSosp);

		     updateMacchina(1);
		     updateMoto(2);
		     
		     SQLConfiguration.getInstance().setAutoCommit();
			
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
	        idGenerato = daoV.insert("insert.veicolo", v);
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
	            idVeicolo,
	            4,
	            "EB523RC",
	            1300	            
	        };

	        daoM.insert("insert.macchina", params);

	        System.out.println("Inserimento macchina OK");

	    } catch (Exception e) {
	        System.out.println("Errore insertMacchina: " + e.getMessage());
	        throw new RuntimeException(e); 
	    }
	}
	

	

	private void insertMacchinaSenzaParametri(int idVeicolo) {


	    System.out.println("*********** Insert into Macchine");

	    try {

	        Object[] params = new Object[] {
	            idVeicolo
	        };

	        daoM.insert("insert.macchina.parametri", params);

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
				righe = daoV.update("update.veicolo", vei);
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

	
	  public int insertColore(String nomeColore) throws AcademyException {
	        try {
	            SQLConfiguration.getInstance().setTransaction();

	            Colore c = new Colore(null, nomeColore); // ID null → DB lo genera
	            int idGenerato = daoC.insert("insert.colore", c);

	            System.out.println("Inserimento colore OK. ID generato: " + idGenerato);

	            SQLConfiguration.getInstance().commit();
	            return idGenerato;

	        } catch (Exception e) {
	            System.out.println("Errore insertColore: " + e.getMessage());
	            SQLConfiguration.getInstance().rollback();
	            throw new AcademyException(e.getMessage());
	        }
	    }
	  
	  public void updateColore(int idColore, String nuovoNome) throws AcademyException {
	        try {
	            SQLConfiguration.getInstance().setTransaction();

	            Colore c = new Colore(idColore, nuovoNome);
	            int righe = daoC.update("update.colore", c);

	            System.out.println("Righe aggiornate: " + righe);

	            SQLConfiguration.getInstance().commit();

	        } catch (Exception e) {
	            System.out.println("Errore updateColore: " + e.getMessage());
	            SQLConfiguration.getInstance().rollback();
	            throw new AcademyException(e.getMessage());
	        }
	  }
	    
	  
	  public void deleteColore(int idColore) throws AcademyException {
	        try {
	            SQLConfiguration.getInstance().setTransaction();

	            daoC.deleteById(idColore);

	            System.out.println("Colore eliminato correttamente. ID: " + idColore);

	            SQLConfiguration.getInstance().commit();

	        } catch (Exception e) {
	            System.out.println("Errore deleteColore: " + e.getMessage());
	            SQLConfiguration.getInstance().rollback();
	            throw new AcademyException(e.getMessage());
	        }
	           
	        
	  }
	  
	  public int insertSospensione(String tipo) throws AcademyException {
		    try {
		        SQLConfiguration.getInstance().setTransaction();
		        Sospensione s = new Sospensione(null, tipo);
		        int idGenerato = daoS.insert("insert.sospensione", s);
		        System.out.println("Inserimento sospensione OK. ID generato: " + idGenerato);
		        SQLConfiguration.getInstance().commit();
		        return idGenerato;
		    } catch (Exception e) {
		        System.out.println("Errore insertSospensione: " + e.getMessage());
		        SQLConfiguration.getInstance().rollback();
		        throw new AcademyException(e.getMessage());
		    }
		}
	  
	  public void updateSospensione(int idSospensione, String nuovoTipo) throws AcademyException {
		    try {
		        SQLConfiguration.getInstance().setTransaction();
		        Sospensione s = new Sospensione(idSospensione, nuovoTipo);
		        int righe = daoS.update("update.sospensione", s);
		        System.out.println("Righe aggiornate: " + righe);
		        SQLConfiguration.getInstance().commit();
		    } catch (Exception e) {
		        System.out.println("Errore updateSospensione: " + e.getMessage());
		        SQLConfiguration.getInstance().rollback();
		        throw new AcademyException(e.getMessage());
		    }
		}
	  
	  public void deleteSospensione(int idSospensione) throws AcademyException {
		    try {
		        SQLConfiguration.getInstance().setTransaction();
		        daoS.deleteById(idSospensione);
		        System.out.println("Sospensione eliminata correttamente. ID: " + idSospensione);
		        SQLConfiguration.getInstance().commit();
		    } catch (Exception e) {
		        System.out.println("Errore deleteSospensione: " + e.getMessage());
		        SQLConfiguration.getInstance().rollback();
		        throw new AcademyException(e.getMessage());
		    }
		}
	  
	  private void insertMoto(int idVeicolo) {

		    System.out.println("***** Insert into Moto");

		    try {

		        Object[] params = new Object[] {
		            idVeicolo,
		            "ZZ98765",
		            1000
		        };

		        daoMO.insert("insert.moto", params);

		        System.out.println("Inserimento moto OK");

		    } catch (Exception e) {
		        System.out.println("Errore insertMoto: " + e.getMessage());
		        throw new RuntimeException(e); 
		    }
		}
		
		private void insertBici(int idVeicolo) {

		    System.out.println("***** Insert into Biciclette");

		    try {

		        Object[] params = new Object[] {
		            idVeicolo,
		            6,	//num marce
		            1,	//id freno fk
		            2	//id sospensione fk
		        };

		        daoB.insert("insert.bici", params);

		        System.out.println("Inserimento moto OK");

		    } catch (Exception e) {
		        System.out.println("Errore insertMoto: " + e.getMessage());
		        throw new RuntimeException(e); 
		    }
		}
		

		
		private void updateMacchina(int id) {
			System.out.println("Update into Macchina*********");
			//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
			Macchina m=new Macchina();
			m.setId(id);
		    m.setPorte(6);
		    m.setCilindrata(1600);

		        int righe;
				try {
					righe = daoM.update("update.macchina", m);
			        System.out.println("Righe aggiornate: " + righe);

				} catch (Exception e) {
					
			        System.out.println("Errore found: " + e.getMessage());
				}

		} 
		
		private void updateMoto(int id) {
			System.out.println("Update into Moto*********");
			//se voglio modificare tutti i campi, altrimenti metto nei commenti i campi da non modificare
			Moto m=new Moto();
			m.setId(id);
		    m.setCilindrata(1600);

		        int righe;
				try {
					righe = daoMO.update("update.moto", m);
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
