package es.unican.is2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.unican.is2.common.Cliente;
import es.unican.is2.common.DataAccessException;

public class ClienteMapper {

	public static Cliente toCliente(ResultSet results) throws DataAccessException {

		Cliente cont =null;
		try {
			String dni = results.getString("dni");
			String nombre = results.getString("nombre");
			boolean minusvalia = results.getBoolean("minusvalia");
			cont = new Cliente();
			cont.setDni(dni);
			cont.setNombre(nombre);	
			cont.setMinusvalia(minusvalia);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}
		return cont;
	}
}
