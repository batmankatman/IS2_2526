package es.unican.is2.main;

import es.unican.is2.common.IClientesDAO;
import es.unican.is2.common.ISegurosDAO;
import es.unican.is2.business.GestionSeguros;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.gui.VistaAgente;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);
	}

}
