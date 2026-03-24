# Práctica 3: Calidad de Producto

Proyecto Seguros (Práctica 2)

## 1. Análisis inicial

El proyecto tiene 374 líneas de código repartidas en 5 módulos. En cuanto a calidad: 0 bugs, 1 vulnerabilidad (rating E), 27 code smells y una deuda técnica de 177 minutos. No hay código duplicado ni tests.

Las incidencias más importantes son:

- Un token de SonarQube escrito en texto plano dentro del pom.xml, lo que es una vulnerabilidad grave (severidad BLOCKER, 30 min de effort).
- Tres campos en VistaAgente.java que deberían declararse transient porque la clase extiende JFrame, que es serializable. Severidad CRITICAL, 30 min c/u.
- Siete campos privados que se asignan en el constructor pero nunca se usan en ningún método. Severidad MAJOR, 5 min c/u.
- Doce campos y variables con nombres que no siguen la convención lowerCamelCase, y seis clases vacías. Severidad MINOR, 2–5 min c/u.

## 2. Plan de mejora

Se priorizan las incidencias de mayor severidad primero, con un presupuesto de 60 minutos.

1. Eliminar el token del pom.xml y pasarlo por línea de comandos (BLOCKER, 30 min). Acumulado: 30 min.
2. Añadir transient a los tres campos de VistaAgente y renombrarlos (CRITICAL, 30 min). Acumulado: 60 min.

Como acciones adicionales fuera del presupuesto: renombrar el resto de campos incorrectos y añadir constructor y getters en Cliente para que sus campos dejen de estar sin usar.

## 3. Resolución de incidencias

Los cambios se aplicaron sobre una copia del proyecto llamada SegurosFinal.

Se resolvieron 17 incidencias: el token del pom.xml, los tres campos transient de VistaAgente, los campos Dni y Nombre de Cliente (se añadieron constructor y getters), y once renombrados de campos y variables en cuatro clases.

Se descartaron 11 incidencias porque corresponden a código intencionalmente incompleto en la Práctica 2: los campos nunca leídos en GestionSeguros y VistaAgente son dependencias que se usarán cuando se implemente la lógica, y las seis clases vacías son entidades o mappers pendientes de desarrollo.

## 4. Análisis final

Resultados en SegurosFinal (http://localhost:9000/dashboard?id=es.unican.is2:SegurosFinal):

- Vulnerabilidades: 1 → 0 (rating E → A)
- Code smells: 27 → 11
- Deuda técnica: 177 min → 55 min (−69%)
- Bugs: 0 → 0, Quality Gate: OK → OK

Se eliminó la vulnerabilidad BLOCKER y las tres incidencias CRITICAL, mejorando el rating de seguridad de E a A y reduciendo la deuda técnica en un 69%. Las 11 incidencias que quedan son stubs pendientes de implementación.

*IA usado para este informe. Supongo estes cambios no son demasiado importantes tan poco...* ¯\\_(ツ)_/¯