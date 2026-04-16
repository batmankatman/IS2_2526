package es.unican.is2.common;

public interface IGestionClientes {
	
	public Cliente nuevoCliente(Cliente c) throws DataAccessException;
	
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException;
		
}
