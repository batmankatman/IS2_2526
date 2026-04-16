**Informe de la práctica 4**

*Planificación del proyecto Seguros*

**Estimación con UCP**

Para la estimación se han considerado dos actores: el *agente de seguros* y el *cliente*. Ambos se clasifican como actores medios, con peso 2, por lo que el valor de **UAW** es:

**UAW = 2 x 2 = 4**

En cuanto a los casos de uso, se han tomado como *simples* los de alta, baja, búsqueda y listado de seguros, todos ellos con peso 5. Como casos de uso *medios* se han considerado alta, baja y búsqueda de cliente, con peso 10.

De ahí sale el siguiente cálculo:

**UUCW = 4 x 5 + 3 x 10 = 50**  
**UUCP = 4 + 50 = 54**

**Factores técnicos**

La aplicación no es distribuida, ya que se trata de una aplicación de escritorio con base de datos H2 local. El tiempo de respuesta requerido es razonable, pero no de tiempo real. La eficiencia de uso se ha valorado de forma positiva porque la interfaz está pensada para una gestión cotidiana sencilla. El procesamiento no es especialmente complejo, aunque sí hay validaciones y acceso a datos. También se ha tenido en cuenta la reutilización gracias al módulo *SegurosCommon*, la facilidad de instalación por usar Maven y H2 embebido, y la portabilidad de Java.

Otros aspectos, como la concurrencia o el acceso de terceros, tienen poco impacto en este proyecto. En conjunto, la suma de factores técnicos es **26.0**, por lo que:

**TCF = 0.6 + (0.01 x 26.0) = 0.86**

**Factores ambientales**

Se ha supuesto un equipo formado por *ingenieros recién graduados*, con buena motivación pero todavía poca experiencia práctica. Eso reduce la familiaridad con el proceso, con el dominio del problema y con el análisis, aunque la motivación es alta y los requisitos son bastante estables porque vienen fijados por el enunciado.

La suma de factores ambientales es **17.0**, así que:

**EF = 1.4 + (-0.03 x 17.0) = 0.89**

**Productividad**

Entre los factores E1 a E6, hay cuatro con valor inferior a 3: E1, E2, E3 y E4. Según la metodología, esto da una productividad de:

**PF = 28 h/UCP**

**Resultado final de la estimación**

Con todos estos valores, el tamaño ajustado del sistema es:

**UCP = 54 x 0.86 x 0.89 = 41.3 UCP**

Tomando la implementación como el 40% del esfuerzo total:

- *Implementación*: **1157 h**
- *Resto de fases* (15% cada una): **434 h** por fase
- **Esfuerzo total estimado: 2893 h**

**Coste por hora**

Para calcular el coste horario se ha usado la plantilla *Plantilla-calculo-hora-trabajador.xlsx*. Se parte de 240 días laborables al año, a los que se restan 25 días de vacaciones, 5 de formación y 10 festivos. Después se aplica un factor del 80%, con lo que quedan **160 días cargables**, equivalentes a **1280 horas al año**.

El coste anual por trabajador se ha estimado así:

- *Sueldo anual*: **3000 x 12 = 36000 EUR**
- *Seguridad Social*: **30%**, es decir, **10800 EUR**
- *Infraestructura por empleado*: **15000 / 5 = 3000 EUR**

Por tanto, el coste horario queda en:

**(46800 + 3000) / 1280 = 38.91 EUR/h**

Como referencia, los recursos del proyecto se mueven en una horquilla aproximada de entre **30 y 52 EUR/h**, ajustando según perfil y responsabilidad.

**Planificación en ProjectLibre**

El proyecto se ha planificado con fecha de inicio el **1 de septiembre de 2026**. Los festivos introducidos en el calendario han sido los siguientes:

- 12/10/26
- 24/12/26
- 25/12/26
- 01/01/27
- 06/01/27

Las fases principales del proyecto son estas:

1. *Requisitos y análisis* (**434 h**): captura, análisis de casos de uso y validación.
2. *Diseño* (**434 h**): diseño arquitectónico, diseño detallado, acceso a datos e interfaz.
3. *Implementación* (**1157 h**): módulos *Common*, *Business*, *DAOH2*, *GUI*, *Main* y estabilización.
4. *Pruebas* (**434 h**): preparación, pruebas unitarias, integración y corrección.
5. *Despliegue* (**434 h**): empaquetado, validación y entrega final.

Además, en el diagrama de Gantt se han incluido varios elementos pedidos en la práctica:

- Tres hitos: *Requisitos cerrados*, *Diseño aprobado* y *Proyecto completado*.
- Un vínculo *comienzo a comienzo* con desfase de 2 días entre *Diseño arquitectónico* y *Diseño detallado*.
- Un adelanto de 2 días entre *Estabilización* y *Preparación de pruebas*.
- Una persona trabajando a media jornada: *Laura Sánchez* al **50%**.
- Un recurso con más de una unidad: *Carlos López* al **200%**.
- Disponibilidad variable para *Pedro Martínez*: **100% de septiembre a noviembre** y **200% de diciembre a enero**.
- Coste variable para *María Fernández*: **48 EUR/h**, que pasa a **52 EUR/h** desde enero de 2027.
- Coste específico por tarea para *Ana García*: **45 EUR/h** en general y **35 EUR/h** en tareas de GUI.

**Validación de la planificación**

Durante la revisión se detectó una sobreasignación de *María Fernández*, que aparecía al **200%** en tareas solapadas por una relación de tipo *CC*. Para corregirlo, se sustituyó su participación en *Diseño detallado* por *Pedro Martínez*.

Con esa corrección, la planificación queda consistente. Las estadísticas finales de coste y duración pueden consultarse en ProjectLibre desde la opción *Vista > Informe*.

**Fichero entregado**

El fichero del proyecto es **Seguros_Project.xml**. Para abrirlo en ProjectLibre, basta con cargarlo y, si se desea, guardarlo después en formato *.pod*.
