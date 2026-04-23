package es.unican.is2.common;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias de la clase Cliente.
 * Metodo bajo prueba: totalSeguros()
 *
 * Tecnicas aplicadas:
 * - Caja negra: particion equivalente y analisis de valores limite
 * - Caja blanca: cobertura de decisiones/condiciones y sentencias
 */
public class ClienteTest {

	private Cliente sut;

	@BeforeEach
	public void setUp() {
		sut = new Cliente();
		sut.setDni("12345678A");
		sut.setNombre("Test");
		sut.setMinusvalia(false);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente sin seguros
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosVacio() {
		assertEquals(0.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente con un seguro
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosUnSeguro() {
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS, 100, null));
		// 100 * 1.5 = 150
		assertEquals(150.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente con varios seguros
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosVariosSeguros() {
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS, 100, null));       // 150
		sut.getSeguros().add(creaSeguro(Cobertura.TODO_RIESGO, 20, "Pepe"));   // 100
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS_LUNAS, 50, null));  // 90
		// Total sin minusvalia: 150 + 100 + 90 = 340
		assertEquals(340.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente con minusvalia (descuento 20%)
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosConMinusvalia() {
		sut.setMinusvalia(true);
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS, 100, null));       // 150
		sut.getSeguros().add(creaSeguro(Cobertura.TODO_RIESGO, 20, "Pepe"));   // 100
		// Total: (150 + 100) * 0.8 = 200
		assertEquals(200.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente con minusvalia sin seguros
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosMinusvaliaVacio() {
		sut.setMinusvalia(true);
		// 0 * 0.8 = 0
		assertEquals(0.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Cliente sin minusvalia con un seguro con conductor
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosConConductorAdicional() {
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS, 100, "Ana"));
		// 100 * 1.5 + 50 = 200
		assertEquals(200.0, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Caja blanca - Ramas: bucle con varios seguros + minusvalia true
	// (refuerza decision/condicion del if minusvalia)
	// -------------------------------------------------------

	@Test
	public void testTotalSegurosMinusvaliaConVariosSeguros() {
		sut.setMinusvalia(true);
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS, 10, null));         // 15
		sut.getSeguros().add(creaSeguro(Cobertura.TERCEROS_LUNAS, 10, null));   // 18
		sut.getSeguros().add(creaSeguro(Cobertura.TODO_RIESGO, 10, "Luis"));    // 75
		// Total: (15 + 18 + 75) * 0.8 = 86.4
		assertEquals(86.4, sut.totalSeguros(), 0.001);
	}

	// -------------------------------------------------------
	// Metodo auxiliar
	// -------------------------------------------------------

	private Seguro creaSeguro(Cobertura cobertura, int potencia, String conductor) {
		Seguro s = new Seguro();
		s.setCobertura(cobertura);
		s.setPotencia(potencia);
		s.setMatricula("TEST001");
		s.setFechaInicio(LocalDate.of(2025, 1, 1));
		s.setConductorAdicional(conductor);
		return s;
	}
}
