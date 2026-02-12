package com.betacom.car.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.betacom.car.models.Marca;
import com.betacom.car.DAO.MarcaDAO;
import com.betacom.car.DAO.MotoDAO;
import com.betacom.car.DAO.VeicoliDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utils.SQLManager;
//import com.betacom.dao.DipendentiDAO;
//import com.betacom.models.Dipendenti;

public class ServicesQuery {
	
	private VeicoliDAO daoV= new VeicoliDAO ();
	private MotoDAO daoM= new MotoDAO ();
	private MarcaDAO daoMarca = new MarcaDAO();

	
	public void executeQuery() throws AcademyException {
		getAllVeicoli();   //GIUSTO
		getVeicoliByTipo(3); //GIUSTO
		getMotoByTarga("XY456ZT"); //GIUSTO
		getAllMarche();

//		getDipendentiWithParameters("impiegato", 1);
//		getDipendenteById(1);  //optional
//		getCount("impiegato");  //count su una query		
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


// MARCHE
	
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

