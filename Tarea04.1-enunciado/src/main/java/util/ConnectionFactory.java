package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ConnectionFactory {
	//Usar patron Sinlgeton: variable privada estatica+constructor privado+ metodo publico estatico
	
	//variable estatica de conexion Neodatis
	private static ODB connection = null;
	//variables estaticas de fichero de datos
	private static final String RUTA_FICHERO_CONFIG = "src/main/resources/db.properties";
	private static final String FILE_KEY = "db.file";
	
	//constructor privado
	private ConnectionFactory() {
		
	}
	
	//metodo publico estatico
	public static ODB getConnection() {
		if (connection == null) {
			Properties properties = new Properties();
			try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_CONFIG)) {
				properties.load(fis);
				String fichero = properties.getProperty(FILE_KEY);

				connection = ODBFactory.open(fichero);
				

			} catch (FileNotFoundException e) {
				System.err.println("Ha ocurrido una excepción FileNotFound: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Ha ocurrido una excepción IOE: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Ha ocurrido una excepción: " + e.getMessage());

			}			
		}
		//devueleve instancia de tipo ODB
		return connection;
	}
	
	//cierra conexion
	public static void closeConnection() {
		if (connection != null) {
			connection.close();
		}
	}
	
	

}
