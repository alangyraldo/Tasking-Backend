package com.tasking.backend.controller; // carpeta de los controladores

import com.tasking.backend.exception.ProjectNotFoundException; // para lanzar el 404
import com.tasking.backend.model.Project; // la entidad
import com.tasking.backend.repository.ProjectRepository; // acceso a la base
import jakarta.validation.Valid; // activa las validaciones de la entidad
import org.springframework.http.HttpStatus; // codigos HTTP
import org.springframework.web.bind.annotation.*; // GetMapping, PostMapping, etc.
import java.util.List; // para devolver varios proyectos

@RestController // responde peticiones HTTP y devuelve JSON
@RequestMapping("/api/projects") // todas las rutas empiezan con /api/projects
public class ProjectController { // controlador de proyectos

    private final ProjectRepository repository; // el repositorio que uso en todos los metodos

    public ProjectController(ProjectRepository repository) { // spring me lo entrega solo (inyeccion de dependencias)
        this.repository = repository; // lo guardo en el campo
    }

    @GetMapping // responde a GET /api/projects
    public List<Project> getAll() { // devuelve todos los proyectos
        return repository.findAll(); // SELECT * FROM projects
    }

    @PostMapping // responde a POST /api/projects
    @ResponseStatus(HttpStatus.CREATED) // devuelve 201 porque se creo algo
    public Project create(@Valid @RequestBody Project project) { // convierte el JSON a Project y lo valida
        project.setId(null); // si el front manda un id lo borro para que mysql cree uno nuevo
        return repository.save(project); // INSERT y devuelve el proyecto ya con su id
    }

    @DeleteMapping("/{id}") // responde a DELETE /api/projects/3
    @ResponseStatus(HttpStatus.NO_CONTENT) // devuelve 204: se borro y no hay nada que mostrar
    public void delete(@PathVariable Long id) { // recibe el id de la ruta
        if (!repository.existsById(id)) { // primero compruebo que exista
            throw new ProjectNotFoundException(id); // si no existe, 404
        }
        repository.deleteById(id); // DELETE FROM projects WHERE id = ?
    }
}