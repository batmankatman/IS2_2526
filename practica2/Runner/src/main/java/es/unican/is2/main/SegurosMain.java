package es.unican.is2.main;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.common.IClientesDAO;
import es.unican.is2.common.ISegurosDAO;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.gui.VistaAgente;

public class SegurosMain {

    public static void main(String[] args) {

        IClientesDAO ClientesDAO = new ClientesDAO();
        ISegurosDAO SegurosDAO = new SegurosDAO();

        GestionSeguros Gestion =
                new GestionSeguros(ClientesDAO, SegurosDAO);

        VistaAgente Vista =
                new VistaAgente(Gestion, Gestion, Gestion);

        Vista.setVisible(true);

    }
}
