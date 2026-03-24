package es.unican.is2.business;

import es.unican.is2.common.IClientesDAO;
import es.unican.is2.common.IGestionClientes;
import es.unican.is2.common.IGestionSeguros;
import es.unican.is2.common.IInfoSeguros;
import es.unican.is2.common.ISegurosDAO;
import es.unican.is2.common.Cliente;
import es.unican.is2.common.Seguro;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private IClientesDAO ClientesDAO;
    private ISegurosDAO SegurosDAO;

    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.ClientesDAO = clientesDAO;
        this.SegurosDAO = segurosDAO;
    }

    public Cliente nuevoCliente(Cliente c) {
        return null;
    }

    public Cliente bajaCliente(String dni) {
        return null;
    }

    public Seguro nuevoSeguro(Seguro s, String dni) {
        return null;
    }

    public Seguro bajaSeguro(String matricula, String dni) {
        return null;
    }

    public Seguro anadeConductorAdicional(String matricula, String conductor) {
        return null;
    }

    public Cliente cliente(String dni) {
        return null;
    }

    public Seguro seguro(String matricula) {
        return null;
    }

}
