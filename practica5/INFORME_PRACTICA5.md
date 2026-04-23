## Informe Práctica 5A

Pruebas unitarias y de integración del proyecto Seguros.

### Fase 1: Pruebas unitarias de las clases del dominio

Se prueban los métodos `precio()` (clase `Seguro`) y `totalSeguros()` (clase `Cliente`). Los métodos get/set quedan excluidos.

**Caja negra** (partición equivalente y análisis de valores límite)

Para `Seguro.precio()` se definieron particiones por tipo de cobertura (TERCEROS, TERCEROS_LUNAS, TODO_RIESGO), por conductor adicional (con valor, null, cadena vacía) y valores límite de potencia (0, 1, valor alto). Resultado: 12 casos de prueba en `SeguroTest`.

Para `Cliente.totalSeguros()` se contemplaron los casos de cliente sin seguros, con un seguro, con varios seguros, con minusvalía con y sin seguros, y con conductor adicional. Resultado: 7 casos en `ClienteTest`.

**Caja blanca** (cobertura de decisión/condición y sentencias)

Los casos diseñados en caja negra ya alcanzan cobertura completa: todas las ramas del switch de cobertura, ambas ramas del conductor adicional, el bucle del for con 0, 1 y N iteraciones, y ambas ramas de la condición de minusvalía.

### Fase 2: Pruebas de integración de VistaAgente

Las pruebas se encuentran en `VistaAgenteIT` y se ejecutan en la fase `verify` de Maven mediante el plugin maven-failsafe. La base de datos H2 en memoria se inicializa con los datos de prueba antes de cada test.

**Casos de prueba (caja negra)**

1. DNI 11111111A (Juan, 3 seguros) → nombre Juan, total 272.5
2. DNI 22222222A (Ana, 1 seguro) → nombre Ana, total 45.0
3. DNI 33333333A (Luis, minusvalía, 0 seguros) → nombre Luis, total 0.0
4. DNI 44444444A (Pepe, 2 seguros) → nombre Pepe, total 600.0
5. DNI inexistente → se muestra mensaje de error
6. DNI vacío → se muestra mensaje de error

**Caja blanca:** los 6 casos cubren todas las ramas de `rellenaDatosCliente()` (cliente encontrado, cliente no encontrado y excepción de acceso a datos).

### Defectos encontrados

En `VistaAgente.rellenaDatosCliente()` el botón Buscar leía `txtNombreCliente.getText()` en lugar de `txtDniCliente.getText()`. Se corrigió durante el proceso de pruebas.

Además, al comenzar la práctica varios métodos eran stubs sin implementar: `Seguro.precio()`, `Cliente.totalSeguros()`, `GestionSeguros.cliente()` y `GestionSeguros.seguro()`. Todos fueron implementados.

*He sido ayudado por Claude IA en este proyecto.*