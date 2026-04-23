package es.unican.is2.gui;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.assertj.swing.edt.GuiActionRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.common.DataAccessException;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.H2ServerConnectionManager;
import es.unican.is2.dao.SegurosDAO;

/**
 * Pruebas de integracion de VistaAgente con negocio y DAO.
 * Caso de uso: Consulta Cliente.
 * Datos iniciales: Tabla 1 del enunciado.
 *
 * Se usa GuiActionRunner para ejecutar las acciones sobre la GUI
 * en el Event Dispatch Thread (EDT) y asi evitar problemas de
 * compatibilidad del robot de AssertJ Swing con JDK recientes.
 */
public class VistaAgenteIT {

	private VistaAgente vista;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtTotal;
	private JButton btnBuscar;

	@BeforeEach
	public void setUp() throws DataAccessException {
		// Inicializar BD H2 antes de crear la GUI
		H2ServerConnectionManager.getConnection();

		GuiActionRunner.execute(() -> {
			ClientesDAO daoClientes = new ClientesDAO();
			SegurosDAO daoSeguros = new SegurosDAO();
			GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
			vista = new VistaAgente(negocio, negocio, negocio);
			vista.setVisible(true);
		});

		// Obtener referencias a los componentes por nombre
		txtDni = findTextField("txtDNICliente");
		txtNombre = findTextField("txtNombreCliente");
		txtTotal = findTextField("txtTotalCliente");
		btnBuscar = findButton("btnBuscar");
	}

	private JTextField findTextField(String name) {
		for (java.awt.Component c : vista.getContentPane().getComponents()) {
			if (c instanceof JTextField && name.equals(c.getName())) {
				return (JTextField) c;
			}
		}
		return null;
	}

	private JButton findButton(String name) {
		for (java.awt.Component c : vista.getContentPane().getComponents()) {
			if (c instanceof JButton && name.equals(c.getName())) {
				return (JButton) c;
			}
		}
		return null;
	}

	/**
	 * Simula la consulta de un cliente: escribe el DNI y pulsa Buscar.
	 */
	private void buscaCliente(String dni) {
		GuiActionRunner.execute(() -> {
			txtDni.setText(dni);
			btnBuscar.doClick();
		});
	}

	// ---------------------------------------------------
	// Caso 1: Juan - 11111111A (3 seguros, sin minusvalia)
	// 15*1.5=22.5 + 20*2.5+50=100 + 100*1.5=150 = 272.5
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteConVariosSeguros() {
		buscaCliente("11111111A");
		assertEquals("Juan", txtNombre.getText());
		assertEquals("272.5", txtTotal.getText());
	}

	// ---------------------------------------------------
	// Caso 2: Ana - 22222222A (1 seguro)
	// 25*1.8 = 45.0
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteConUnSeguro() {
		buscaCliente("22222222A");
		assertEquals("Ana", txtNombre.getText());
		assertEquals("45.0", txtTotal.getText());
	}

	// ---------------------------------------------------
	// Caso 3: Luis - 33333333A (minusvalia, 0 seguros)
	// Total: 0.0
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteSinSeguros() {
		buscaCliente("33333333A");
		assertEquals("Luis", txtNombre.getText());
		assertEquals("0.0", txtTotal.getText());
	}

	// ---------------------------------------------------
	// Caso 4: Pepe - 44444444A (2 seguros)
	// 40*1.5=60 + 300*1.8=540 = 600.0
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteConDosSeguros() {
		buscaCliente("44444444A");
		assertEquals("Pepe", txtNombre.getText());
		assertEquals("600.0", txtTotal.getText());
	}

	// ---------------------------------------------------
	// Caso 5: DNI que no existe
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteNoExiste() {
		buscaCliente("99999999Z");
		assertEquals("Error en BBDD", txtNombre.getText());
		assertEquals("", txtTotal.getText());
	}

	// ---------------------------------------------------
	// Caso 6: DNI vacio
	// ---------------------------------------------------
	@Test
	public void testConsultaClienteDniVacio() {
		GuiActionRunner.execute(() -> {
			txtDni.setText("");
			btnBuscar.doClick();
		});
		assertEquals("Error en BBDD", txtNombre.getText());
		assertEquals("", txtTotal.getText());
	}
}
