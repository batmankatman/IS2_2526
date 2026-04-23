package es.unican.is2.common;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias de la clase Seguro.
 * Metodo bajo prueba: precio()
 *
 * Tecnicas aplicadas:
 * - Caja negra: particion equivalente y analisis de valores limite
 * - Caja blanca: cobertura de decisiones/condiciones y sentencias
 */
public class SeguroTest {

	private Seguro sut;

	// -------------------------------------------------------
	// Caja negra - Particion equivalente por tipo de cobertura
	// -------------------------------------------------------

	@Test
	public void testPrecioTerceros() {
		sut = creaSeguro(Cobertura.TERCEROS, 100, null);
		assertEquals(150.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioTercerosLunas() {
		sut = creaSeguro(Cobertura.TERCEROS_LUNAS, 100, null);
		assertEquals(180.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioTodoRiesgo() {
		sut = creaSeguro(Cobertura.TODO_RIESGO, 100, null);
		assertEquals(250.0, sut.precio(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Efecto del conductor adicional
	// -------------------------------------------------------

	@Test
	public void testPrecioConConductorAdicional() {
		sut = creaSeguro(Cobertura.TERCEROS, 100, "Pepe");
		assertEquals(200.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioSinConductorAdicionalNull() {
		sut = creaSeguro(Cobertura.TERCEROS, 100, null);
		assertEquals(150.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioSinConductorAdicionalVacio() {
		sut = creaSeguro(Cobertura.TERCEROS, 100, "");
		assertEquals(150.0, sut.precio(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Valores limite de potencia
	// -------------------------------------------------------

	@Test
	public void testPrecioPotencia1() {
		sut = creaSeguro(Cobertura.TERCEROS, 1, null);
		assertEquals(1.5, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioPotenciaAlta() {
		sut = creaSeguro(Cobertura.TODO_RIESGO, 300, null);
		assertEquals(750.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioPotencia0() {
		sut = creaSeguro(Cobertura.TERCEROS, 0, null);
		assertEquals(0.0, sut.precio(), 0.001);
	}

	// -------------------------------------------------------
	// Caja negra - Combinaciones cobertura + conductor adicional
	// -------------------------------------------------------

	@Test
	public void testPrecioTercerosLunasConConductor() {
		sut = creaSeguro(Cobertura.TERCEROS_LUNAS, 50, "Ana");
		assertEquals(140.0, sut.precio(), 0.001);
	}

	@Test
	public void testPrecioTodoRiesgoConConductor() {
		sut = creaSeguro(Cobertura.TODO_RIESGO, 20, "Luis");
		assertEquals(100.0, sut.precio(), 0.001);
	}

	// -------------------------------------------------------
	// Caja blanca - Cobertura de todas las ramas del switch
	// y de la condicion del conductor adicional
	// (los tests anteriores ya cubren todas las sentencias
	// y decisiones; estos refuerzan combinaciones)
	// -------------------------------------------------------

	@Test
	public void testPrecioTercerosConConductorPotenciaBaja() {
		sut = creaSeguro(Cobertura.TERCEROS, 10, "Maria");
		assertEquals(65.0, sut.precio(), 0.001);
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
