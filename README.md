Perfecto. Aquí tienes **la definición formal del proyecto**, escrita **como si fuera un proyecto final de universidad**, con alcance, objetivos, arquitectura, módulos, entregables y la participación clara de **todas** las tecnologías que mencionaste.
No contiene código. Solo definición profesional.

---

# **PROYECTO FINAL – Plataforma San Benito**

### *Sistema integral de catálogo, pedidos, logística y monitoreo para un marketplace de artesanías*

**Duración del proyecto:** 2 semanas

------------------------------------

## **1. Descripción general del proyecto**

**San Benito** es una plataforma completa de comercio electrónico dirigida a pequeños negocios de artesanía. El proyecto consiste en diseñar e implementar un sistema backend modular y escalable, basado en microservicios, que permita gestionar:

* Productos y colecciones religosas
* Usuarios y roles
* Pedidos y pagos parciales
* Coordinación logística (entregas, costo del motorizado)
* Notificaciones asíncronas
* Integraciones externas (REST, SOAP, mensajería)
* Monitoreo y observabilidad centralizada
* Pipeline de CI/CD
* Procesamiento de eventos en tiempo real

La plataforma debe desarrollarse utilizando **Spring Boot con Java y Maven**, y seguir principios de **Arquitectura Hexagonal / Clean Architecture**. Además, se debe estructurar el código bajo un **monorepo** con módulos claramente definidos y posibilidad de despliegue independiente (microservicios).

---

## **2. Objetivo general**

Diseñar y desarrollar una plataforma backend integral que permita aplicar y demostrar dominio en prácticas modernas de arquitectura de software, integración de servicios, mensajería, pruebas automatizadas, observabilidad y despliegue continuo.

---

## **3. Objetivos específicos**

1. Aplicar **arquitectura hexagonal** para separar dominio, aplicación y adaptadores.
2. Implementar un **monorepo Maven**, con módulos independientes que representen microservicios reales.
3. Modelar datos usando **MongoDB**, aprovechando documentos embebidos e índices.
4. Desarrollar APIs **REST** documentadas con Swagger/OpenAPI.
5. Implementar un servicio legacy o simulado con **SOAP** y conectarlo al ecosistema.
6. Integrar mensajería mediante **Kafka** (eventos del dominio) y **RabbitMQ** (tareas asíncronas).
7. Diseñar un pipeline de **CI/CD** que compile, ejecute pruebas y despliegue los servicios.
8. Incorporar herramientas de monitoreo: **ELK**, **Prometheus**, **Grafana**.
9. Aplicar buenas prácticas de ramas, commits y documentación del ciclo de desarrollo.
10. Realizar pruebas automatizadas (Mockito) y pruebas funcionales con Postman.

---

## **4. Alcance del proyecto**

### **4.1. Funcionalidades principales del sistema**

#### **Módulo 1: Gestión de usuarios**

* Registro, login y roles (ADMIN, VENDEDOR, CLIENTE).
* JWT tokens.
* Consulta de información del usuario.

#### **Módulo 2: Catálogo de artesanías**

* CRUD de productos, colecciones y stock.
* Búsqueda por categorías, nombre o precio.
* Asociación a imágenes (se guarda solo metadata, no archivos).

#### **Módulo 3: Gestión de pedidos**

* Crear pedido con productos seleccionados.
* Registrar pagos parciales (adelanto y saldo).
* Cambiar estado del pedido (pendiente, enviado, entregado).
* Cálculo de costo de entrega según zona.

#### **Módulo 4: Logística**

* Estimación del costo y tiempo de entrega.
* Integración con un servicio externo SOAP simulado (“Verificador de motorizado”).
* Publicación de eventos cuando cambia el estado del envío.

#### **Módulo 5: Notificaciones**

* Envío asíncrono de correos o mensajes simulados (cola RabbitMQ).
* Confirmación de pedido.
* Confirmación de pago.
* Estado del envío.

#### **Módulo 6: Analítica del sistema**

* Procesamiento de **eventos Kafka**:

  * Pedido creado
  * Pago recibido
  * Pedido despachado
* Los eventos se almacenan en un submódulo especializado o en MongoDB para dashboards.

---

## **4.2. Alcance técnico**

### **Spring Boot + Java + Maven**

Cada módulo debe implementarse como un microservicio independiente dentro de un monorepo.
Cada microservicio debe incluir:

* Lógica de dominio (clean/hexagonal)
* Adaptadores REST, persistencia y mensajería
* Configuración externalizada
* Exposición de `/actuator` para métricas

---

### **Arquitectura Hexagonal / Clean Architecture**

Todos los servicios deben tener:

* **Capa de dominio:** entidades, agregados, validaciones.
* **Capa de aplicación:** casos de uso y orquestación.
* **Capa de infraestructura:** adaptadores para DB, REST, SOAP, Kafka, Rabbit.
* **Puertos (interfaces):** para lograr inversión de dependencias.

Esta separación permite probar el núcleo del sistema sin infraestructura.

---

### **Monorepo + Modularización**

* Un solo repositorio Git.
* Módulos Maven:

  * `service-user`
  * `service-catalog`
  * `service-order`
  * `service-logistics`
  * `service-notification`
  * `service-analytics`
  * `api-gateway`
  * `common-library`
* Cada servicio tiene su propio `application.yml`, rutas y responsabilidades.

---

### **MongoDB**

Se utiliza como base de datos principal por su flexibilidad y facilidad para modelar documentos como:

* Productos
* Pedidos con items embebidos
* Eventos del dominio
* Usuarios

Cada microservicio tiene su propia base o colección independiente (pattern: **Database per Service**).

---

### **REST y OpenAPI (Swagger)**

* Cada microservicio expone endpoints REST.
* Toda API debe documentarse con OpenAPI 3 (Swagger).
* Se debe generar documentación navegable en `/swagger-ui.html`.

---

### **SOAP**

El módulo de logística implementa una integración SOAP para simular un sistema legado de verificación de servicio de motorizado.

* Se expone o consume un servicio SOAP.
* Se usa dentro de un adaptador en la capa hexagonal.
* Se implementa un timeout y reintento.

---

### **Kafka**

Se usa para **eventos del dominio**, especialmente:

* `ORDER_CREATED`
* `PAYMENT_COMPLETED`
* `ORDER_SHIPPED`

Productores: Order Service, Logistics Service
Consumidor: Analytics Service

Objetivo: manejar eventual consistency, integrar microservicios y simular arquitectura distribuida real.

---

### **RabbitMQ**

Se usa para **procesos asíncronos**, como:

* Envío de notificaciones
* Procesos de verificación
* Generación de reportes

Hay colas específicas, DLQ y reintentos.

---

### **Testing (Mockito + Postman)**

* Pruebas unitarias para casos de uso (Mockito).
* Pruebas de integración con Spring Boot.
* Pruebas manuales/automatizadas de endpoints usando colecciones de Postman.

---

### **CI/CD**

Debe implementarse con una plataforma moderna (GitHub Actions o GitLab CI).

Pipeline mínimo:

1. Compilar con Maven
2. Ejecutar pruebas unitarias
3. Generar artefactos y empaquetar contenedores Docker
4. Publicar imágenes en registry
5. Desplegar a un entorno (Docker Compose / Kubernetes local)

Entrega final debe incluir archivos YAML del pipeline.

---

### **ELK Stack (ElasticSearch, Logstash, Kibana)**

Para **centralización de logs**:

* Cada microservicio envía logs en formato JSON.
* Logstash los procesa.
* Kibana muestra visualizaciones:

  * Errores por servicio
  * Flujo de pedidos
  * Eventos por minuto

---

### **Prometheus y Grafana**

Para **métricas**:

* Cada servicio expone métricas vía `/actuator/prometheus`.
* Prometheus scrapea todas las métricas.
* Grafana construye dashboards:

  * Latencia del microservicio
  * Consumo de memoria
  * Cantidad de pedidos por segundo
  * Lag de consumidores Kafka

---

## **5. Arquitectura general del sistema**

La arquitectura final incluye:

1. **Microservicios independientes**
   Desarrollados bajo arquitectura limpia.

2. **Bases de datos independientes (MongoDB)**
   Aislando responsabilidad por servicio.

3. **Event-driven architecture (Kafka)**
   Sube eventos que otros servicios consumen de forma eventual.

4. **Procesos asíncronos (RabbitMQ)**
   Para operaciones no bloqueantes o que necesitan reintento.

5. **Integración externa (SOAP)**
   Simulación de sistema legacy.

6. **Observabilidad y monitoreo**
   Logs → ELK
   Métricas → Prometheus
   Dashboards → Grafana

7. **Pipeline de CI/CD**
   Desde commit hasta despliegue automático.

---

## **6. Supuestos y limitaciones**

* El frontend no es parte del alcance; el enfoque es completamente backend.
* No se requieren pasarelas reales de pago.
* El SOAP es simulado, no real.
* La disponibilidad no tiene que ser productiva, solo suficiente para demostrar el diseño.
* No se exige autenticación OAuth corporativa, solo JWT.

---

## **7. Entregables finales**

1. **Monorepo completo en GitHub**

   * Código organizado por módulos
   * Commits semánticos
   * Ramas limpias

2. **Swagger/OpenAPI completo**

   * Exportado en formato YAML/JSON

3. **Colección de Postman**

   * Documentación ejecutable de los endpoints

4. **Pipeline de CI/CD funcional**

5. **Docker Compose + Kubernetes (opcional)**

   * Archivos de infraestructura

6. **Dashboard de Grafana y paneles de Kibana configurados**

---

## **8. Validación del proyecto**

Para aprobar el proyecto se evaluará:

| Criterio                                      | Peso |
| --------------------------------------------- | ---- |
| Arquitectura hexagonal correctamente aplicada | 20%  |
| Uso correcto de microservicios y monorepo     | 15%  |
| Integración con MongoDB                       | 10%  |
| APIs REST + documentación OpenAPI             | 10%  |
| Integración Kafka + RabbitMQ                  | 15%  |
| CI/CD funcionando                             | 10%  |
| Observabilidad (ELK + Prometheus + Grafana)   | 10%  |
| Calidad del documento final y presentación    | 10%  |

