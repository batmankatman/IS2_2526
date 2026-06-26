# Informe Práctica 5A - Pruebas de Software

Asignatura: Ingeniería del Software II  
Curso: 2025/2026  
Nombre: <!-- añadir nombre aquí -->

## Fase 1: Pruebas unitarias de las clases de dominio

### Clase Seguro — método precio()

Se aplicó partición equivalente y análisis de valores límite (caja negra). Las particiones identificadas son:

**cobertura:** TODO_RIESGO, TERCEROS_LUNAS, TERCEROS (válidas) / null (no válida)

**potencia:** [1, 89] sin multiplicador, [90, 110] ×1,05, >110 ×1,20 (válidas) / ≤0 (no válida). Valores límite: 89, 90, 110, 111.

**antigüedad:** <1 año sin descuento, [1, 3) años −10%, ≥3 años −20% (válidas).

Casos de prueba:

- CP1: TODO_RIESGO, potencia=80, fechaInicio=hoy → 1000,00
- CP2: TERCEROS_LUNAS, potencia=95, hace 1 año → 567,00
- CP3: TERCEROS, potencia=115, hace 2 años → 432,00
- CP4: TERCEROS, potencia=125, hace 4 años → 384,00
- CP5 (AVL): TODO_RIESGO, potencia=89, hoy → 1000,00
- CP6 (AVL): TODO_RIESGO, potencia=90, hoy → 1050,00
- CP7 (AVL): TODO_RIESGO, potencia=110, hoy → 1050,00
- CP8 (AVL): TODO_RIESGO, potencia=111, hoy → 1200,00
- CP9 (NV): potencia=0 → OperacionNoValida
- CP10 (NV): cobertura=null → OperacionNoValida

Caja blanca: los casos anteriores cubren todos los ramos del switch (TODO_RIESGO, TERCEROS_LUNAS, TERCEROS), los tres rangos de potencia y los tres rangos de antigüedad. Se añadió un caso default al switch que lanza OperacionNoValida para evitar un camino muerto que inicializaba precioBase a 0.

### Clase Cliente — método totalSeguros()

Se aplicó partición equivalente (caja negra). Particiones: lista de seguros vacía, un seguro válido, varios seguros válidos, seguro con datos inválidos (excepción ignorada por el catch interno), y minusvalia true/false.

Casos de prueba:

- CP1: sin seguros, minusvalia=false → 0,00
- CP2: TERCEROS p=80 sin descuento, minusvalia=false → 400,00
- CP3: TODO_RIESGO p=80 + TERCEROS_LUNAS p=100 1 año, minusvalia=false → 1567,00
- CP4: TODO_RIESGO p=80, minusvalia=true → 800,00
- CP5: cobertura=null (inválido), minusvalia=false → 0,00

Caja blanca: los casos cubren el bucle vacío, el bucle con seguros válidos, el catch de OperacionNoValida y la condición minusvalia.

## Fase 2: Pruebas de integración de VistaAgente

Se aplicó partición equivalente (caja negra) usando AssertJ Swing (FrameFixture) para la interacción con la interfaz. La prueba integra VistaAgente con GestionSeguros, ClientesDAO y SegurosDAO sobre una base de datos H2 en memoria. La clase de test se ubica en el módulo SegurosMain para no romper la independencia entre capas.

Particiones: DNI existente en la base de datos / DNI inexistente.

Casos de prueba:

- IT1: DNI=11111111A → nombre="Juan", total=1456.0
- IT2: DNI=00000000X → nombre="Error en BBDD", total=""

Caja blanca: los dos casos cubren ambas ramas del método rellenaDatosCliente (cliente encontrado y excepción/null).

Las pruebas unitarias se ejecutan en la fase test con maven-surefire-plugin y las de integración en la fase verify con maven-failsafe-plugin. Todos los casos pasan sin errores.
