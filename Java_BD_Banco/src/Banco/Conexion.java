package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import quick.dbtable.DBTable;

public class Conexion {
	
	private static final String SERVIDOR = "localhost:3306";
	private static final String BASE_DATOS = "banco";
	private static final String uriConexion = "jdbc:mysql://" + SERVIDOR + "/" + BASE_DATOS+"?serverTimezone=UTC";
	private Connection conexionBD = null;
    private String usuario;
    private String clave;
    
	public Conexion(String usuario,String clave) {
		
		this.usuario = usuario;
		this.clave = clave;
	}

	public Connection conectarBD()
	{
		if (this.conexionBD == null)
		   {
		     
		 	  try
		      {
		        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				    this.conexionBD = DriverManager.getConnection(uriConexion, usuario, clave);
		      }
		      catch (Exception ex)
		 	  {}
		   }
		
		return conexionBD;
	}

	
}
