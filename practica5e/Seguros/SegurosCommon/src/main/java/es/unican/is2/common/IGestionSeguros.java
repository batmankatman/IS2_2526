package es.unican.is2.common;

public interface IGestionSeguros {
	
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException;
	
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException;

	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException;

}
