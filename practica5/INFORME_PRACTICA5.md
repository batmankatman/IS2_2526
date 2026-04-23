**Informe de la práctica 5A**

*Pruebas unitarias y de integración del proyecto Seguros*

---

**Fase 1: Pruebas unitarias de las clases del dominio**

Se prueban los métodos `precio()` (clase `Seguro`) y `totalSeguros()` (clase `Cliente`). Los métodos *get/set* quedan excluidos.

*Caja negra — partición equivalente y AVL*

Para `Seguro.precio()`: particiones por tipo de cobertura (TERCEROS, TERCEROS_LUNAS, TODO_RIESGO), por conductor adicional (con valor, null, cadena vacía) y valores límite de potencia (0, 1, valor alto). Total: **12 casos** en `SeguroTest`.

Para `Cliente.totalSeguros()`: cliente sin seguros, con un seguro, con varios seguros, con minusvalía (con y sin seguros), con conductor adicional. Total: **7 casos** en `ClienteTest`.

*Caja blanca — cobertura de decisión/condición y sentencias*

Los casos de caja negra ya alcanzan cobertura completa: todas las ramas del switch de cobertura, ambas ramas del conductor adicional, el bucle del for (0, 1 y N iteraciones) y ambas ramas de la condición de minusvalía.

---

**Fase 2: Pruebas de integración de VistaAgente**

Clase: `VistaAgenteIT`, ejecutada en la fase `verify` de Maven con *maven-failsafe-plugin*. La BD H2 en memoria se inicializa con los datos de la Tabla 1.

*Caja negra — 6 casos de prueba:*

| # | Entrada | Resultado esperado |
|---|---------|-------------------|
| 1 | 11111111A (Juan, 3 seguros) | nombre=Juan, total=272.5 |
| 2 | 22222222A (Ana, 1 seguro) | nombre=Ana, total=45.0 |
| 3 | 33333333A (Luis, minusvalía, 0 seguros) | nombre=Luis, total=0.0 |
| 4 | 44444444A (Pepe, 2 seguros) | nombre=Pepe, total=600.0 |
| 5 | DNI inexistente | mensaje de error |
| 6 | DNI vacío | mensaje de error |

*Caja blanca:* los 6 casos cubren todas las ramas de `rellenaDatosCliente()` (cliente encontrado, no encontrado, excepción).

---

**Defectos encontrados**

- `VistaAgente.rellenaDatosCliente()`: el botón *Buscar* leía `txtNombreCliente.getText()` en lugar de `txtDniCliente.getText()`. Corregido.
- Stubs implementados: `Seguro.precio()`, `Cliente.totalSeguros()`, `GestionSeguros.cliente()`, `GestionSeguros.seguro()`.

He usado Claude IA por ayuda en este proyecto.