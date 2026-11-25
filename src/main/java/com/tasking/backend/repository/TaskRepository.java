package com.tasking.backend.repository; // carpeta de los repositorios

import com.tasking.backend.model.Task; // importo la entidad con la que va a trabajar
import org.springframework.data.jpa.repository.JpaRepository; // interfaz de Spring que ya trae los métodos CRUD

public interface TaskRepository extends JpaRepository<Task, Long> { // Task es la entidad y Long el tipo de su id
    // vacío a propósito: con solo extender JpaRepository ya tengo findAll, findById, save, deleteById y existsById sin escribir SQL
}