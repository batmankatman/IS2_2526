package es.unican.is2.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Equivalence partitions for Cliente.totalSeguros():
//   seguros: lista vacía (v), 1 seguro válido (v), varios seguros válidos (v), seguro inválido (v, lanzaría excepción)
//   minusvalia: false (v), true (v)
class ClienteTest {

    private Cliente cSinSeguros;        // sin seguros, minusvalia=false
    private Cliente cUnSeguro;          // 1 seguro TERCEROS p=80 sin descuento -> 400.0
    private Cliente cVariosSeguros;     // TODO_RIESGO p=80 (1000) + TERCEROS_LUNAS p=100 1yr (567) -> 1567.0
    private Cliente cMinusvalia;        // TODO_RIESGO p=80 sin descuento, minusvalia=true -> 1000*0.80=800.0
    private Cliente cSeguroInvalido;    // 1 seguro con cobertura=null -> precio() lanza excepción -> 0.0

    @BeforeEach
    void setUp() {
        cSinSeguros = new Cliente();
        cSinSeguros.setDni("00000000A");
        cSinSeguros.setNombre("Sin Seguros");
        cSinSeguros.setMinusvalia(false);

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS);
        s1.setPotencia(80);
        s1.setFechaInicio(LocalDate.now());

        cUnSeguro = new Cliente();
        cUnSeguro.setDni("11111111A");
        cUnSeguro.setNombre("Juan");
        cUnSeguro.setMinusvalia(false);
        cUnSeguro.getSeguros().add(s1);

        Seguro s2 = new Seguro();
        s2.setCobertura(Cobertura.TODO_RIESGO);
        s2.setPotencia(80);
        s2.setFechaInicio(LocalDate.now());

        Seguro s3 = new Seguro();
        s3.setCobertura(Cobertura.TERCEROS_LUNAS);
        s3.setPotencia(100);
        s3.setFechaInicio(LocalDate.now().minusYears(1));

        cVariosSeguros = new Cliente();
        cVariosSeguros.setDni("22222222A");
        cVariosSeguros.setNombre("Ana");
        cVariosSeguros.setMinusvalia(false);
        cVariosSeguros.getSeguros().add(s2);
        cVariosSeguros.getSeguros().add(s3);

        Seguro s4 = new Seguro();
        s4.setCobertura(Cobertura.TODO_RIESGO);
        s4.setPotencia(80);
        s4.setFechaInicio(LocalDate.now());

        cMinusvalia = new Cliente();
        cMinusvalia.setDni("33333333A");
        cMinusvalia.setNombre("Luis");
        cMinusvalia.setMinusvalia(true);
        cMinusvalia.getSeguros().add(s4);

        Seguro sInvalido = new Seguro();
        sInvalido.setCobertura(null);  // cobertura null -> precio() lanza OperacionNoValida
        sInvalido.setPotencia(80);

        cSeguroInvalido = new Cliente();
        cSeguroInvalido.setDni("44444444A");
        cSeguroInvalido.setNombre("Inválido");
        cSeguroInvalido.setMinusvalia(false);
        cSeguroInvalido.getSeguros().add(sInvalido);
    }

    @Test
    void testTotalSeguros() {
        // Sin seguros -> 0.0
        assertEquals(0.0, cSinSeguros.totalSeguros(), 0.01);

        // 1 seguro: TERCEROS, p=80, sin descuento -> 400.0
        assertEquals(400.0, cUnSeguro.totalSeguros(), 0.01);

        // Varios seguros: TODO_RIESGO p=80 (1000) + TERCEROS_LUNAS p=100 1yr (600*1.05*0.90=567) -> 1567.0
        assertEquals(1567.0, cVariosSeguros.totalSeguros(), 0.01);

        // Minusvalia: TODO_RIESGO p=80 sin descuento (1000) * 0.80 -> 800.0
        assertEquals(800.0, cMinusvalia.totalSeguros(), 0.01);

        // Seguro inválido (cobertura=null): excepción ignorada -> 0.0
        assertEquals(0.0, cSeguroInvalido.totalSeguros(), 0.01);
    }
}
