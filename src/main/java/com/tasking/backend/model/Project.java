package com.tasking.backend.model; // carpeta de las entidades

import jakarta.persistence.*; // anotaciones de JPA para crear la tabla
import jakarta.validation.constraints.NotBlank; // valida que un texto no este vacio
import jakarta.validation.constraints.Size; // valida el largo de un texto

@Entity // esta clase se convierte en una tabla de la base de datos
@Table(name = "projects") // nombre de la tabla en MySQL
public class Project { // cada objeto Project es una fila de la tabla

    @Id // llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql genera el id solo con auto_increment, reemplaza al currentId++ de ProjectManager
    private Long id; // identificador unico del proyecto

    @NotBlank(message = "el nombre no puede estar en blanco") // si llega vacio responde 400
    @Size(min = 3, message = "el nombre debe tener minimo 3 caracteres") // mismo minimo que valida el front
    private String nombre; // nombre del proyecto

    @Column(length = 1000) // por defecto un String es VARCHAR(255), aqui permito descripciones mas largas
    private String descripcion; // descripcion opcional, por eso no lleva validacion

    public Project() {} // constructor vacio que necesita JPA

    public Long getId() { return id; } // devuelve el id
    public void setId(Long id) { this.id = id; } // cambia el id

    public String getNombre() { return nombre; } // devuelve el nombre
    public void setNombre(String nombre) { this.nombre = nombre; } // cambia el nombre

    public String getDescripcion() { return descripcion; } // devuelve la descripcion
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } // cambia la descripcion
}