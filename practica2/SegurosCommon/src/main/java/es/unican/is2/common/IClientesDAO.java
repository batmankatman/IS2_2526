package es.unican.is2.common;

public interface IClientesDAO {

	Cliente[] clientes();

	Cliente cliente(String dni);

	Cliente creaCliente(Cliente c);

	Cliente actualizaCliente(Cliente nuevo);

	Cliente eliminaCliente(String dni);

}
