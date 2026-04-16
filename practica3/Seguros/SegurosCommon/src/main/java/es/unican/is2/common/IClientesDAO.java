package es.unican.is2.common;

import java.util.List;

public interface IClientesDAO  {
	
	public Cliente creaCliente(Cliente c) throws DataAccessException;
	
	public Cliente cliente(String dni) throws DataAccessException;
	
	public Cliente actualizaCliente(Cliente nuevo) throws DataAccessException;
	
	public Cliente eliminaCliente(String dni) throws DataAccessException;
	
	public List<Cliente> clientes() throws DataAccessException;
	
}
