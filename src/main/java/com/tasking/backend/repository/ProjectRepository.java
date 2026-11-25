package com.tasking.backend.repository; // carpeta de los repositorios

import com.tasking.backend.model.Project; // la entidad
import org.springframework.data.jpa.repository.JpaRepository; // trae los metodos CRUD ya hechos

public interface ProjectRepository extends JpaRepository<Project, Long> { // Project es la entidad y Long el tipo de su id
    // vacio a proposito: ya trae findAll, findById, save, deleteById y existsById
}