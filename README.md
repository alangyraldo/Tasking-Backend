# Tasking Backend

API REST con Spring Boot, JPA y MySQL para el planificador de tareas Tasking.

## Requisitos
- Java 21
- MySQL

## Cómo correrlo
1. Crear la base de datos en MySQL:
   `CREATE DATABASE task_planner_db;`
2. Revisar usuario y contraseña en `src/main/resources/application.properties`
   (por defecto root / root, o variables de entorno DB_USER y DB_PASSWORD).
3. Ejecutar `./gradlew bootRun` o correr `BackendApplication` desde el IDE.
4. La API queda en `http://localhost:8080`.

## Endpoints
- `GET, POST /api/tasks` y `PUT, DELETE /api/tasks/{id}`
- `GET, POST /api/projects` y `DELETE /api/projects/{id}`

El frontend está en el repo `Tasking` y usa CORS para conectarse desde Live Server.