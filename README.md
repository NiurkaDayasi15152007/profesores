# Proyecto Spring Boot - Profesores y Asignaturas

Aplicacion web desarrollada con Spring Boot que gestiona Profesores y Asignaturas con relacion OneToMany.

## Tecnologias
- Java 25
- Spring Boot 4.0.6
- Spring Data JPA
- Thymeleaf
- H2 Database (en memoria)
- Gradle

## Requisitos
- Java 17 o superior instalado
- Gradle (incluido con gradlew)

## Configuracion de base de datos
La aplicacion usa H2 en memoria, no requiere instalacion. La consola H2 esta disponible en:
http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:profesoresdb
- Usuario: SA
- Password: (vacio)

## Como ejecutar
1. Clonar el repositorio:
   git clone https://github.com/NiurkaDayasi15152007/profesores.git
2. Entrar en la carpeta:
   cd profesores
3. Ejecutar:
   ./gradlew bootRun

La aplicacion arranca en http://localhost:8080

## Interfaz web
- Profesores: http://localhost:8080/web/profesores
- Asignaturas: http://localhost:8080/web/asignaturas

## API REST
- GET    /api/profesores
- POST   /api/profesores
- GET    /api/profesores/{id}
- PUT    /api/profesores/{id}
- DELETE /api/profesores/{id}

## Modelos
### Profesor
- nombre, especialidad, anosExperiencia
- Un profesor tiene muchas asignaturas (OneToMany)

### Asignatura
- nombre, curso, horasSemanales, tipo (teoria/practica)
- Una asignatura pertenece a un profesor (ManyToOne)