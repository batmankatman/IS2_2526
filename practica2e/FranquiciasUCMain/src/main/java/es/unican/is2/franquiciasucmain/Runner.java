package es.unican.is2.franquiciasucmain;
import es.unican.is2.franquiciasuccommon.*;
import es.unican.is2.franquiciasucbusiness.*;
import es.unican.is2.franquiciasucdao.*;
import es.unican.is2.franquiciasucgui.*;
import es.unican.is2.franquiciasucbusiness.*;
import es.unican.is2.franquiciasucdao.*;
import es.unican.is2.franquiciasucgui.*;


/**
 * Clase principal que construye la aplicación de tres capas y lanza su ejecución
 */
public class Runner {

	public static void main(String[] args) {
		// Crear componentes capa DAO
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		// Crear componentes capa negocio
		GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);
		
		// Crear componentes capa presentacion
		VistaGerente vista = new VistaGerente(gTiendas, gEmpleados);
		
		// Lanzar ejecución (hacer visible la interfaz)
		vista.setVisible(true);
		
	
		
	}

}
