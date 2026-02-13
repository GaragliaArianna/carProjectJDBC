package com.betacom.car.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.betacom.car.models.Alimentazione;
import com.betacom.car.models.Categoria;

import com.betacom.car.models.Marca;
import com.betacom.car.DAO.MarcaDAO;

import com.betacom.car.DAO.ColoriDAO;
import com.betacom.car.DAO.FreniDAO;
import com.betacom.car.DAO.ColoriDAO;
import com.betacom.car.DAO.MacchineDAO;
import com.betacom.car.models.Marca;
import com.betacom.car.DAO.MarcaDAO;

import com.betacom.car.DAO.MotoDAO;
import com.betacom.car.DAO.SospensioniDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;

import com.betacom.car.models.Colore;
import com.betacom.car.models.Freno;
import com.betacom.car.models.Marca;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Sospensione;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.DAO.AlimentazioniDAO;
import com.betacom.car.DAO.CategoriaDAO;
import com.betacom.car.DAO.ColoriDAO;
import com.betacom.car.DAO.MarcaDAO;
import com.betacom.car.DAO.MotoDAO;
import com.betacom.car.DAO.SospensioniDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;


//import com.betacom.dao.DipendentiDAO;
//import com.betacom.models.Dipendenti;

public class ServicesQuery {
	
	private VeicoliDAO daoV= new VeicoliDAO ();
	private MotoDAO daoM= new MotoDAO ();

	private MarcaDAO daoMarca = new MarcaDAO();

	private MacchineDAO daoMA= new MacchineDAO ();

	private ColoriDAO daoC= new ColoriDAO ();
	private SospensioniDAO daoS = new SospensioniDAO();

	private AlimentazioniDAO daoA = new AlimentazioniDAO();
	private CategoriaDAO daoCat = new CategoriaDAO();
	private FreniDAO daoFreno = new FreniDAO(); 


   

	public void executeQuery() throws AcademyException {
		getAllVeicoli();   //GIUSTO
		getVeicoliByTipo(3); //GIUSTO
		getMotoByTarga("XY456ZT"); //GIUSTO
		
		getAllColori();
		getColoreById(1);

		getAllSospensioni();
		getSospensioneById(1);

		getAllMarche();

		getAllAlimentazioni();
		getAlimentazioneById(2);
		
		getAllCategorie();
		getCategoriaById(2);

		
		getMacchinaByTarga("AB123CD");
		
		 getAllFreni();
		    

	}

	
	private void getAllVeicoli() throws AcademyException {
		System.out.println("*****getAll Veicoli******");
		String query=SQLConfiguration.getInstance().getQuery("query.veicoli");
		System.out.println(query);
		
		try {
			List <Veicoli> lV=daoV.findAll();
			lV.forEach(l -> System.out.println(l));
		} catch (Exception e) {
			System.out.println("Error found: "+e.getMessage());
		}
	}
	
	
	
	private void getVeicoliByTipo(Integer idTipoVeicolo) {
		System.out.println("*****getAll Veicoli by Tipo *****");
		try {
			List <Veicoli> lV=daoV.findByTipo("query.veicoli.byTipo", new Object[] {
					idTipoVeicolo
			});
			lV.forEach(l -> System.out.println(l));
		} catch (Exception e) {
			System.out.println("Error found: "+e.getMessage());
		}
	}
	
	private void getMotoByTarga(String targa) {
	    System.out.println("*****get moto by targa");
	    try {
	        Optional<Moto> moto = daoM.findMotoByTarga(new Object[]{targa});
	        if (moto.isEmpty()) {
	            System.out.println("Moto non trovata: " + targa);
	        } else {
	            System.out.println(moto.get());
	        }
	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

	private void getAllColori() {

	    System.out.println("*****getAll Colori******");

	    try {
	        List<Colore> lC = daoC.findAll();
	        lC.forEach(c -> System.out.println(c));

	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}
	
	private void getColoreById(Integer idColore) {

	    System.out.println("*****get Colore by ID******");

	    try {

	        Optional<Colore> colore = daoC.findById(idColore);

	        if (colore.isEmpty()) {
	            System.out.println("Colore non trovato: " + idColore);
	        } else {
	            System.out.println(colore.get());
	        }

	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

// MARCHE
	
	private void getAllSospensioni() {
	    System.out.println("*****getAll Sospensioni******");

	    try {
	        List<Sospensione> lS = daoS.findAll();
	        lS.forEach(s -> System.out.println(s));


	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

	
    public void getAllMarche() {
        System.out.println("***** Get All Marche *****");
        try {
            List<Marca> marche = daoMarca.findAll();
            if (marche.isEmpty()) {
                System.out.println("Nessuna marca trovata.");
            } else {
                marche.forEach(m -> System.out.println(m.getIdMarca() + " - " + m.getMarca()));
            }
        } catch (AcademyException e) {
            System.out.println("Error during getAllMarche: " + e.getMessage());
        }
    }




	private void getSospensioneById(Integer idSospensione) {
	    System.out.println("*****get Sospensione by ID******");

	    try {
	        Optional<Sospensione> sosp = daoS.findById(idSospensione);

	        if (sosp.isEmpty()) {
	            System.out.println("Sospensione non trovata: " + idSospensione);
	        } else {
	            System.out.println(sosp.get());
	        }

	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}
	
	private void getAllAlimentazioni() {
	    System.out.println("*****getAll Alimentazioni******");

	    try {
	        List<Alimentazione> list = daoA.findAll();
	        list.forEach(a -> System.out.println(a));

	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

	private void getAlimentazioneById(Integer id) {
	    System.out.println("*****get Alimentazione by ID******");

	    try {
	        Optional<Alimentazione> a = daoA.findById(id);

	        if (a.isEmpty())
	            System.out.println("Alimentazione non trovata: " + id);
	        else
	            System.out.println(a.get());

	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

	private void getAllCategorie() {
	    System.out.println("**getAll Categorie***");
	    try {
	        List<Categoria> lC = daoCat.findAll();
	        lC.forEach(c -> System.out.println(c));
	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}

	private void getCategoriaById(Integer idCategoria) {
	    System.out.println("**get Categoria by ID***");
	    try {
	        Optional<Categoria> cat = daoCat.findById(idCategoria);
	        if (cat.isEmpty()) {
	            System.out.println("Categoria non trovata: " + idCategoria);
	        } else {
	            System.out.println(cat.get());
	            
	        } } catch(Exception e) {
	        	//poi capiamo
	        } }
	  
	            

	private void getMacchinaByTarga(String targa) {
	    System.out.println("*****get macchina by targa");
	    try {
	        Optional<Macchina> macchina = daoMA.findMacchinaByTarga(new Object[]{targa});
	        if (macchina.isEmpty()) {
	            System.out.println("Macchina non trovata: " + targa);
	        } else {
	            System.out.println(macchina.get());

	        }
	    } catch (Exception e) {
	        System.out.println("Error found: " + e.getMessage());
	    }
	}
	


public void getAllFreni() {
    System.out.println(" Get All Freni ");
    try {
        List<Freno> freni = daoFreno.findAll();
        if (freni.isEmpty()) {
            System.out.println("Nessun freno trovato.");
        } else {
            freni.forEach(f -> System.out.println(f.getIdFreno() + " - " + f.getFreno()));
        }
    } catch (AcademyException e) {
        System.out.println("Error during getAllFreni: " + e.getMessage());
    }
}
}

	
	
//	private void getAllDipendenti() throws AcademyException {
//		System.out.println("*****getAll Dipendenti");
//		String query=SQLConfiguration.getInstance().getQuery("query.dipendenti");
//		System.out.println(query);
//		/*
//		List<Map<String, Object>> res=db.list(query);
//		
//		System.out.println("Numero di righe: "+ res.size());
//		
//		for(Map<String, Object> it: res) {
//			System.out.println(it.get("nome")+ " " + it.get("cognome") );
//
//		}*/
//		
//		try {
//			List <Dipendenti> lD=dao.findAll();
//			lD.forEach(l -> System.out.println(l));
//		} catch (Exception e) {
//			System.out.println("Error found: "+e.getMessage());
//		}
//	}
		
//	private void getDipendentiWithParameters(String param) {
//		System.out.println("*****getAll Dipendenti con parametro");
//		try {
//			List <Dipendenti> lD=dao.findGeneric("query.dipendenti.mansione", new Object[] {
//					param
//			});
//			lD.forEach(d -> System.out.println(d));
//		} catch (Exception e) {
//			System.out.println("Error found: "+e.getMessage());
//		}
//	}
//	
//	private void getDipendentiWithParameters(String param, Integer uff) {
//		System.out.println("*****getAll Dipendenti con ufficio");
//		try {
//			List <Dipendenti> lD=dao.findGeneric("query.dipendenti.ufficio", new Object[] {
//					param, uff
//			});
//			lD.forEach(d -> System.out.println(d));
//		} catch (Exception e) {
//			System.out.println("Error found: "+e.getMessage());
//		}
//	}
//	
//	private void getDipendenteById(Integer id) {
//		System.out.println("*****get Dipendente by id");
//		try {
//			Optional <Dipendenti> dip=dao.findById(new Object[] {id});
//			if (dip.isEmpty())  
//				System.out.println("Dipendente non trovato: "+id);
//			else
//				System.out.println(dip.get());
//		} catch (Exception e) {
//			System.out.println("Error found: "+e.getMessage());
//		}
//	}
//	
//	private void getCount(String param) {
//		System.out.println("*****getAll Dipendenti con parametro");
//		try {
//			Long c=dao.count("query.dipendenti.mansione", new Object[] {
//					param
//			});
//			System.out.println("count  "+c);
//		} catch (Exception e) {
//			System.out.println("Error found: "+e.getMessage());
//		}
//	}
//	
//	

