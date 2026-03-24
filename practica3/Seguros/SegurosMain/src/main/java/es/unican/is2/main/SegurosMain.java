package es.unican.is2.main;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.common.IClientesDAO;
import es.unican.is2.common.ISegurosDAO;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.gui.VistaAgente;

public class SegurosMain {

    public static void main(String[] args) {

        IClientesDAO clientesDAO = new ClientesDAO();
        ISegurosDAO segurosDAO = new SegurosDAO();

        GestionSeguros gestion =
                new GestionSeguros(clientesDAO, segurosDAO);

        VistaAgente vista =
                new VistaAgente(gestion, gestion, gestion);

        vista.setVisible(true);

    }
}
