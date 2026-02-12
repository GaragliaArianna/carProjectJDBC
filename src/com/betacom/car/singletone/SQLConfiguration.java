package com.betacom.car.singletone;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.utils.SQLManager;

/*
 * Questa classe SQLConfiguration è un Singleton che gestisce la configurazione del database e la connessione JDBC.
 * Lavora insieme a SQLManager:
 * SQLConfiguration → configura e fornisce la connessione
 * SQLManager → esegue le query
 */

public class SQLConfiguration {

	private static SQLConfiguration instance=null;
	
	/*
	 * i dati dei 2 file vengono caricati in due oggetti Properties:
	 * private static Properties prop;
	 * private static Properties query;
	 */
	private static Properties prop=new Properties();
	private static Properties query=new Properties();
	
	private Connection con=null;
	
	private SQLConfiguration () {
	}
	
	public static SQLConfiguration getInstance() throws AcademyException {
		if (instance ==null) {
			instance = new SQLConfiguration();
			
			loadConfiguration();
			
		}
		return instance;
	}
	
	private static void loadConfiguration() throws AcademyException {
		
		try {
			InputStream input = new FileInputStream("src/sql.properties");
			prop.load(input);
			
			InputStream sql = new FileInputStream("src/query.properties");
			query.load(sql);
			
		} catch (IOException e) {
			throw new AcademyException(e.getMessage());
		}
		
	}
	
	public String getProperty(String p) {
		return prop.getProperty(p);
	}
	
	public String getQuery(String p) {
		return query.getProperty(p);
	}
	
	public Connection getConnection() throws AcademyException {
		if (con == null) {
			con=new SQLManager().getConnection();
		}
		return con;
		
	}
	
	public void setAutoCommit() throws SQLException {
		con.setAutoCommit(true);
	}
	
	public void setTransaction() throws SQLException {
		con.setAutoCommit(false);
	}
	
	public void commit () throws AcademyException {
		try {
			con.commit();		
		
		} catch (SQLException e ) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public void rollback() throws AcademyException {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new AcademyException (e.getMessage());
		}
	}
	
	/*
	 * chiusura connessione
	 */
	public void closeConnection() throws AcademyException{
		try {
			if (con != null) 
				con.close();
			con=null;
		} catch (Exception e){
			throw new AcademyException ("close connection error"+e.getLocalizedMessage());
		}
	}
	
}




