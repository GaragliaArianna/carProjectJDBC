package com.betacom.car.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singletone.SQLConfiguration;



public class SQLManager {

	public Connection getConnection() throws AcademyException {
		Connection con=null;
	
		
		try {
			Class.forName(SQLConfiguration.getInstance().getProperty("driver")); //load jdbc driver using reflection
			//apertura del db con url user e pwd
			con=DriverManager.getConnection(
				SQLConfiguration.getInstance().getProperty("url"),
				SQLConfiguration.getInstance().getProperty("user"),
				SQLConfiguration.getInstance().getProperty("pwd")

			);
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		
	}
		
		return con;
		
	}
	
	/*
	 * recupero dei metadati
	 * Recupera tutte le tabelle del database
	* Ritorna una List<String> con i nomi delle tabelle
	 */
	
	public List<String> listOfTable (String dbName) throws AcademyException{
		List<String> lt = new ArrayList<String>();
		
		try {
			DatabaseMetaData dbMD=SQLConfiguration.getInstance().getConnection().getMetaData();
			
			ResultSet res=dbMD.getTables (dbName, null, null, null);
			
			//build del risultato
			while (res.next()) {
				lt.add(res.getString("TABLE_name"));
				
			}
			
			
		} catch (SQLException e) {
			throw new AcademyException (e.getMessage());
		}
		
		return lt;
	}
	
	/*
	 * query select without parameter
	 * restituisce una riga Map in cui:
	 * chiave = nome colonna
	 * valore = valore del campo
	 */
	
	public List<Map<String, Object>>  list(String query) throws AcademyException {
		try {
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			ResultSet res=cmd.executeQuery();
			return resultsetToList(res);
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	
	}
	
	/*
	 * query con parametro
	 */
	public List<Map<String, Object>> list(String query, Object[]params) throws AcademyException {
		try {
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			
			cmd=createSet(cmd, params); //load parameters into query object
			
			ResultSet res=cmd.executeQuery();
			return resultsetToList(res);
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	
	}
	
	/*
	 * count with parameters
	 */
	public Long count(String query, Object[]params) throws AcademyException {
		try {
			
			String qryCount="select count(*) as numero from ("+query+") as numero";
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(qryCount);
			
			cmd=createSet(cmd, params); //load parameters into query object
			
			ResultSet res=cmd.executeQuery();
			res.next();
			return res.getLong("numero");
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	/*
	 * insert object without primary key
	 * Esegue INSERT, UPDATE o DELETE 
	 * Ritorna il numero di righe modificate
	 */
	public int save(String query, Object[]params) throws AcademyException {
		int ret=0;
		
		try {
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			cmd=createSet(cmd, params);
			
			ret=cmd.executeUpdate();
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		return ret;
	}
	
	/*
	 * save with primary key
	 * Come sopra
	 * Ma recupera anche la chiave primaria generata automaticamente
	 * Usa Statement.RETURN_GENERATED_KEYS
	 * Restituisce l’id generato
	 */
	
	public int save(String query, Object[]params, boolean pk) throws AcademyException {
		int ret=0;
		
		try {
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			cmd=createSet(cmd, params);
			
			ret=cmd.executeUpdate();
			
			ResultSet generatedKeys=cmd.getGeneratedKeys();
			if (generatedKeys.next()) {
				ret=generatedKeys.getInt(1);
			} else throw new AcademyException("Generated key non valida");
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		return ret;
	}
	
	
	/*
	 * load parameters into preparated statement
	 */
	private PreparedStatement createSet(PreparedStatement cmd, Object [] params) throws SQLException {
		int idx=1;
		for (Object o:params) {
			cmd.setObject(idx++, o);  //scorre i diversi parametri, ad esempio mansione ufficio ecc
		}
		
		return cmd;
		
	}
	
	/*
	 * query with single result object
	 */
	public Map<String, Object> get(String query, Object[]params) throws AcademyException {
		try {
			PreparedStatement cmd=SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			
			cmd=createSet(cmd, params); //load parameters into query object
			
			ResultSet res=cmd.executeQuery();
			return resultsetToMap(res);
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	
	}
	
	/*
	 * transform result set into map list
	 */
	private List<Map<String, Object>> resultsetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md=rs.getMetaData(); //retrieve resultSet Metadata
		
		//numero colonne tabella della tabella
		int columns=md.getColumnCount();
		
		List<Map<String, Object>> rows=new ArrayList<Map<String, Object>>();
		
		while(rs.next()) {
			Map<String, Object> row= new HashMap<String, Object> ();
			for (int i=1; i<=columns; i++) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			rows.add(row);
		}
				
				
				
		return rows;
		
	}
	

	/*
	 * transform resultSet in map
	 */
	private Map<String, Object> resultsetToMap(ResultSet rs) throws SQLException {
		ResultSetMetaData md=rs.getMetaData(); //retrieve resultSet Metadata
		
		//numero colonne tabella della tabella
		int columns=md.getColumnCount();
		
		if(!rs.next()) {
			return null;
		}
			
	
			Map<String, Object> row= new HashMap<String, Object> ();
			for (int i=1; i<=columns; i++) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}

				
				
		return row;
		
	}
	
	/*
	 * commit: conferma la transazione
	 */
	public void commit() throws AcademyException, SQLException{
		SQLConfiguration.getInstance().getConnection().commit();
	}
	
	/*
	 * rollback : annulla la transazione
	 */

	public void rollback() throws AcademyException, SQLException{
		SQLConfiguration.getInstance().getConnection().rollback();
	}

	/*
	 * delete with parameters
	 * Esegue una DELETE
	 * Restituisce il numero di righe eliminate
	 */
	public int delete(String query, Object[] params) throws AcademyException {

	    int ret = 0;

	    try {

	        PreparedStatement cmd =
	            SQLConfiguration.getInstance().getConnection()
	            .prepareStatement(query);

	        cmd = createSet(cmd, params);

	        ret = cmd.executeUpdate(); // numero righe eliminate

	    } catch (Exception e) {
	        throw new AcademyException(e.getMessage());
	    }

	    return ret;
	}

	
}
