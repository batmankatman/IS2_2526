package es.unican.is2.gui;

import javax.swing.JFrame;

import es.unican.is2.common.IGestionClientes;
import es.unican.is2.common.IGestionSeguros;
import es.unican.is2.common.IInfoSeguros;

public class VistaAgente extends JFrame {

    private IGestionClientes GestionClientes;
    private IGestionSeguros GestionSeguros;
    private IInfoSeguros InfoSeguros;

    public VistaAgente(IGestionClientes clientes, IGestionSeguros seguros, IInfoSeguros info) {
        this.GestionClientes = clientes;
        this.GestionSeguros = seguros;
        this.InfoSeguros = info;
    }

}
