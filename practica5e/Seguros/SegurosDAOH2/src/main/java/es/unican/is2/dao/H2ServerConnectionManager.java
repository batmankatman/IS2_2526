package es.unican.is2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import es.unican.is2.common.DataAccessException;

public class H2ServerConnectionManager {

	protected static Connection connection;

	protected static String dbName = "test";
	protected static String user = "sa";
	protected static String pass = "";

	public static Connection getConnection() throws DataAccessException {

		if (connection == null) { 
			try {
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa","");		
				cargaDatos();
			} catch (SQLException | ClassNotFoundException e) {
				throw new DataAccessException();
			}
		}
		return connection;
	}

	public static void cargaDatos() throws DataAccessException {
		try {
			Connection con = getConnection();
			
			Statement stm = con.createStatement(); 
			
			String sql= "CREATE TABLE Clientes (dni CHAR(9) NOT NULL, nombre VARCHAR(100) NOT NULL, "
					+ "minusvalia BOOLEAN NOT NULL, PRIMARY KEY(dni))";
			stm.execute(sql); 
			
			sql  = "CREATE TABLE Seguros (id BIGINT NOT NULL AUTO_INCREMENT, matricula CHAR(7) NOT NULL, fechaInicio DATE NOT NULL, "
					+ "cobertura VARCHAR(100) NOT NULL, potencia INT NOT NULL, conductorAdicional VARCHAR(100), cliente_FK CHAR(9) NOT NULL, "
					+ "PRIMARY KEY(id), FOREIGN KEY(cliente_FK) REFERENCES Clientes(dni))";

			stm.execute(sql); 		
			
			sql = "INSERT INTO Clientes (dni, nombre, minusvalia) VALUES ('11111111A', 'Juan', false)"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Clientes (dni, nombre, minusvalia) VALUES ('22222222A', 'Ana', false)"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Clientes (dni, nombre, minusvalia) VALUES ('33333333A', 'Luis', true)"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Clientes (dni, nombre, minusvalia) VALUES ('44444444A', 'Pepe', false)"; 
			stm.executeUpdate(sql);
			
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, cliente_FK) "
					+ "VALUES ('1111AAA', '2002-01-15', 'TERCEROS', 15, '11111111A')";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, conductorAdicional, cliente_FK) "
					+ "VALUES ('1111BBB', '2016-05-20', 'TODO_RIESGO', 20, 'Pepe', '11111111A')";
			stm.executeUpdate(sql);			
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, cliente_FK) "
					+ "VALUES ('1111CCC', '2022-05-21', 'TERCEROS', 100, '11111111A')";
			stm.executeUpdate(sql);
			
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, cliente_FK) "
					+ "VALUES ('2222AAA', '2010-06-01', 'TERCEROS_LUNAS', 25, '22222222A')";
			stm.executeUpdate(sql);
			
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, cliente_FK) "
					+ "VALUES ('4444AAA', '2024-01-02', 'TERCEROS', 40, '44444444A')";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Seguros (matricula, fechaInicio, cobertura, potencia, cliente_FK) "
					+ "VALUES ('4444BBB', '2024-01-02', 'TERCEROS_LUNAS', 300, '44444444A')";
			stm.executeUpdate(sql);
			
			stm.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			throw new DataAccessException();
			
		} 		
	}

	public static void executeSqlStatement(String stringStatement) throws DataAccessException {
		Connection con = getConnection(); 
		try {
			Statement stm = con.createStatement(); 
			stm.execute(stringStatement); 
			stm.close(); 
		}
		catch (SQLException e) {
			throw new DataAccessException(); 
		}
	}
}
