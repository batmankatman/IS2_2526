import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GestionTransportesTest {

	@Test
	public void testMejoresConductores() {
		GestionTransportes gt = new GestionTransportes();
		gt.anhadeConductor("11111111A", "Ana", "Lopez", null, "Calle 1");
		gt.anhadeConductor("22222222B", "Luis", "Perez", null, "Calle 2");

		Conductor conductorA = gt.buscaConductor("11111111A");
		Conductor conductorB = gt.buscaConductor("22222222B");
		conductorA.anhadeTransporte(new Transporte(1, CategoriaTransporte.Personas, 1));
		conductorB.anhadeTransporte(new Transporte(1, CategoriaTransporte.Mercancias_Peligrosas, 1));

		List<Conductor> resultado = gt.mejoresConductores();
		assertEquals(1, resultado.size());
		assertEquals("22222222B", resultado.get(0).dni());
		assertEquals("Luis Perez", resultado.get(0).toString());
	}
}