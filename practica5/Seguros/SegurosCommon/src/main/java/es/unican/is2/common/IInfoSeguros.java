package es.unican.is2.common;

public interface IInfoSeguros {
	
	public Cliente cliente(String dni) throws DataAccessException; 
	
	public Seguro seguro(String matricula) throws DataAccessException;

}
