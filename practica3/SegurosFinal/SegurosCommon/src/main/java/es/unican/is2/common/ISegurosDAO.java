package es.unican.is2.common;

import java.util.List;

public interface ISegurosDAO  {
	
	public Seguro creaSeguro(Seguro v) throws DataAccessException;
	
	public Seguro eliminaSeguro(long id) throws DataAccessException;
	
	public Seguro actualizaSeguro(Seguro nuevo) throws DataAccessException;
	
	public Seguro seguro(long id) throws DataAccessException;

	public Seguro seguroPorMatricula(String matricula) throws DataAccessException;
	
	public List<Seguro> seguros() throws DataAccessException;
}
