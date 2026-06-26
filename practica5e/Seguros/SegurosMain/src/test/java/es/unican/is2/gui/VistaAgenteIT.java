package es.unican.is2.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import es.unican.is2.business.GestionSeguros;
import es.unican.is2.common.Cliente;
import es.unican.is2.common.Seguro;
import es.unican.is2.dao.ClientesDAO;
import es.unican.is2.dao.SegurosDAO;
import es.unican.is2.common.DataAccessException;
import es.unican.is2.common.OperacionNoValida;

class VistaAgenteIT {

    private FrameFixture window;
    private ClientesDAO clientesDAO;

    @BeforeAll
    static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    void setUp() throws Exception {
        FailOnThreadViolationRepaintManager.install();
        clientesDAO = new ClientesDAO();
        SegurosDAO segurosDAO = new SegurosDAO();
        GestionSeguros gestionSeguros = new GestionSeguros(clientesDAO, segurosDAO);

        VistaAgente frame = GuiActionRunner.execute(() -> new VistaAgente(gestionSeguros, gestionSeguros, gestionSeguros));
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    // Helper: set DNI field and fire the search button action directly on the EDT,
    // bypassing robot mouse/keyboard simulation (which requires macOS Accessibility permissions).
    private void buscar(String dni) {
        GuiActionRunner.execute(() -> {
            JTextField dniField = (JTextField) window.textBox("txtDNICliente").target();
            dniField.setText(dni);
            JButton btn = (JButton) window.button("btnBuscar").target();
            for (ActionListener al : btn.getActionListeners()) {
                al.actionPerformed(new ActionEvent(btn, ActionEvent.ACTION_PERFORMED, ""));
            }
        });
    }

    @Test
    void testBusquedaValida() throws DataAccessException, OperacionNoValida {
        buscar("11111111A");

        Cliente c = clientesDAO.cliente("11111111A");
        double expectedTotal = 0;
        for (Seguro s : c.getSeguros()) {
            expectedTotal += s.precio();
        }

        assertEquals("Juan", window.textBox("txtNombreCliente").text());
        assertEquals(String.valueOf(expectedTotal), window.textBox("txtTotalCliente").text());
    }

    @Test
    void testBusquedaInvalida() {
        buscar("00000000X");

        assertEquals("Error en BBDD", window.textBox("txtNombreCliente").text());
        assertEquals("", window.textBox("txtTotalCliente").text());
    }
}
