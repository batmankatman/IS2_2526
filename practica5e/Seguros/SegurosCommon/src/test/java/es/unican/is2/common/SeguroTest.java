package es.unican.is2.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Equivalence partitions for Seguro.precio():
//   cobertura:   TODO_RIESGO (v), TERCEROS_LUNAS (v), TERCEROS (v), null (nv)
//   potencia:    [1,89] no multiplier (v), [90,110] x1.05 (v), >110 x1.20 (v), <=0 (nv)
//   antiguedad:  <1 yr no discount (v), [1,3) yr -10% (v), >=3 yr -20% (v)
class SeguroTest {

    // One representative per valid cobertura x potencia range x antiguedad range
    private Seguro sTodoRiesgo;      // TODO_RIESGO, p=80 [1-89], fechaInicio=now (sin descuento)
    private Seguro sTercerosLunas;   // TERCEROS_LUNAS, p=95 [90-110], 1 yr (-10%)
    private Seguro sTerceros;        // TERCEROS, p=115 >110, 2 yr (-10%)
    private Seguro sTercerosViejo;   // TERCEROS, p=125 >110, 4 yr (-20%)

    @BeforeEach
    void setUp() {
        sTodoRiesgo = new Seguro();
        sTodoRiesgo.setCobertura(Cobertura.TODO_RIESGO);
        sTodoRiesgo.setPotencia(80);
        sTodoRiesgo.setFechaInicio(LocalDate.now());

        sTercerosLunas = new Seguro();
        sTercerosLunas.setCobertura(Cobertura.TERCEROS_LUNAS);
        sTercerosLunas.setPotencia(95);
        sTercerosLunas.setFechaInicio(LocalDate.now().minusYears(1));

        sTerceros = new Seguro();
        sTerceros.setCobertura(Cobertura.TERCEROS);
        sTerceros.setPotencia(115);
        sTerceros.setFechaInicio(LocalDate.now().minusYears(2));

        sTercerosViejo = new Seguro();
        sTercerosViejo.setCobertura(Cobertura.TERCEROS);
        sTercerosViejo.setPotencia(125);
        sTercerosViejo.setFechaInicio(LocalDate.now().minusYears(4));
    }

    // Valid equivalence partitions
    @Test
    void testSeguroValido() throws OperacionNoValida {
        // TODO_RIESGO, p=80 (sin multiplicador), sin descuento -> 1000.0
        assertEquals(1000.0, sTodoRiesgo.precio(), 0.01);
        // TERCEROS_LUNAS, p=95 (+5%), 1 yr (-10%) -> 600*1.05*0.90 = 567.0
        assertEquals(567.0, sTercerosLunas.precio(), 0.01);
        // TERCEROS, p=115 (+20%), 2 yr (-10%) -> 400*1.20*0.90 = 432.0
        assertEquals(432.0, sTerceros.precio(), 0.01);
        // TERCEROS, p=125 (+20%), 4 yr (-20%) -> 400*1.20*0.80 = 384.0
        assertEquals(384.0, sTercerosViejo.precio(), 0.01);
    }

    // Boundary values for potencia ranges: 89, 90, 110, 111
    @Test
    void testLimitesPotencia() throws OperacionNoValida {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TODO_RIESGO);
        s.setFechaInicio(LocalDate.now());

        s.setPotencia(89);  // limite superior [1,89] -> sin multiplicador -> 1000.0
        assertEquals(1000.0, s.precio(), 0.01);

        s.setPotencia(90);  // limite inferior [90,110] -> x1.05 -> 1050.0
        assertEquals(1050.0, s.precio(), 0.01);

        s.setPotencia(110); // limite superior [90,110] -> x1.05 -> 1050.0
        assertEquals(1050.0, s.precio(), 0.01);

        s.setPotencia(111); // limite inferior >110 -> x1.20 -> 1200.0
        assertEquals(1200.0, s.precio(), 0.01);
    }

    // Invalid equivalence partitions
    @Test
    void testSeguroNoValido() {
        // potencia=0 es invalido
        Seguro sPotenciaCero = new Seguro();
        sPotenciaCero.setCobertura(Cobertura.TODO_RIESGO);
        sPotenciaCero.setPotencia(0);
        assertThrows(OperacionNoValida.class, () -> sPotenciaCero.precio());

        // cobertura=null es invalido
        Seguro sCoberturaNull = new Seguro();
        sCoberturaNull.setCobertura(null);
        sCoberturaNull.setPotencia(80);
        assertThrows(OperacionNoValida.class, () -> sCoberturaNull.precio());
    }

}
