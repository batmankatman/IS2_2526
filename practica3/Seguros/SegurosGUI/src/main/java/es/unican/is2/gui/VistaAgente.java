package es.unican.is2.gui;

import javax.swing.JFrame;

import es.unican.is2.common.IGestionClientes;
import es.unican.is2.common.IGestionSeguros;
import es.unican.is2.common.IInfoSeguros;

public class VistaAgente extends JFrame {

    private transient IGestionClientes gestionClientes;
    private transient IGestionSeguros gestionSeguros;
    private transient IInfoSeguros infoSeguros;

    public VistaAgente(IGestionClientes clientes, IGestionSeguros seguros, IInfoSeguros info) {
        this.gestionClientes = clientes;
        this.gestionSeguros = seguros;
        this.infoSeguros = info;
    }

}
