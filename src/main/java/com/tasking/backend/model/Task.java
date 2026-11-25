package com.tasking.backend.model; // carpeta donde vive esta clase

import jakarta.persistence.*; // anotaciones de JPA (Entity, Id, Table...), con ellas Hibernate crea la tabla
import jakarta.validation.constraints.NotBlank; // valida que un texto no sea null ni vacío
import jakarta.validation.constraints.NotNull; // valida que un campo no sea null
import jakarta.validation.constraints.Size; // valida el largo mínimo o máximo de un texto
import java.time.LocalDate; // tipo para fechas sin hora, equivale al input type="date" del front

@Entity // le dice a JPA que esta clase se convierte en una tabla de la base de datos
@Table(name = "tasks") // nombre de la tabla en MySQL
public class Task { // cada objeto Task es una fila de la tabla

    @Id // marca este campo como la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL genera el id solo con AUTO_INCREMENT, reemplaza mi currentId++
    private Long id; // identificador único de la tarea

    @NotBlank(message = "el nombre no puede estar en blanco") // mismo mensaje que validation.js cuando está vacío
    @Size(min = 6, message = "el nombre debe tener mas de 5 caracteres") // min 6 equivale a mi regla length <= 5 es error
    private String nombre; // título de la tarea, el front lo pinta en el h3 de la card

    @NotBlank(message = "la descripcion no puede estar en blanco") // igual que en el front
    @Size(min = 6, message = "la descripcion debe tener mas de 5 caracteres") // mas de 5, igual que el front
    @Column(length = 1000) // por defecto un String en MySQL es VARCHAR(255), aquí permito descripciones más largas
    private String descripcion; // detalle de la tarea, la card lo muestra en gris

    @NotBlank(message = "el campo 'asignado a' no puede estar en blanco") // obligatorio, y evita que charAt(0) del avatar falle con null
    @Size(min = 4, message = "el campo 'asignado a' debe tener mas de 3 caracteres") // mas de 3, igual que el front
    private String asignadoA; // persona responsable, mismo nombre que usa ui.js

    @NotNull(message = "debes seleccionar una fecha de entrega") // mismo mensaje que validation.js
    private LocalDate fechaDeEntrega; // se llama igual que task.fechaDeEntrega de ui.js, así no hay que traducir nada

    @NotBlank(message = "debes seleccionar un estado") // toda tarea debe tener estado para saber en qué columna va
    private String estado; // guarda el mismo valor que el front, por ejemplo HECHO

    private Long proyectoId; // a qué proyecto pertenece, por ahora es un número suelto sin relación

    public Task() {} // constructor vacío, JPA lo necesita para crear objetos al leer de la base

    public Long getId() { return id; } // devuelve el id, Jackson lo usa para armar el JSON
    public void setId(Long id) { this.id = id; } // cambia el id

    public String getNombre() { return nombre; } // devuelve el nombre
    public void setNombre(String nombre) { this.nombre = nombre; } // cambia el nombre

    public String getDescripcion() { return descripcion; } // devuelve la descripción
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } // cambia la descripción

    public String getAsignadoA() { return asignadoA; } // devuelve el responsable
    public void setAsignadoA(String asignadoA) { this.asignadoA = asignadoA; } // cambia el responsable

    public LocalDate getFechaDeEntrega() { return fechaDeEntrega; } // devuelve la fecha de entrega
    public void setFechaDeEntrega(LocalDate fechaDeEntrega) { this.fechaDeEntrega = fechaDeEntrega; } // cambia la fecha de entrega

    public String getEstado() { return estado; } // devuelve el estado
    public void setEstado(String estado) { this.estado = estado; } // cambia el estado

    public Long getProyectoId() { return proyectoId; } // devuelve el proyecto
    public void setProyectoId(Long proyectoId) { this.proyectoId = proyectoId; } // cambia el proyecto
}