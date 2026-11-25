package com.tasking.backend.controller; // carpeta de los controladores

import com.tasking.backend.exception.TaskNotFoundException; // para lanzar el 404
import com.tasking.backend.model.Task; // la entidad
import com.tasking.backend.repository.TaskRepository; // el acceso a la base
import jakarta.validation.Valid; // activa las validaciones @NotBlank y @NotNull
import org.springframework.http.HttpStatus; // códigos de estado HTTP
import org.springframework.web.bind.annotation.*; // GetMapping, PostMapping, etc.
import java.util.List; // para devolver varias tareas

@RestController // esta clase responde peticiones HTTP y devuelve JSON, no vistas HTML
@RequestMapping("/api/tasks") // todas las rutas de la clase empiezan con /api/tasks
public class TaskController { // controlador de las tareas

    private final TaskRepository repository; // guardo el repositorio para usarlo en todos los métodos

    public TaskController(TaskRepository repository) { // Spring me da el repositorio solo (inyección de dependencias)
        this.repository = repository; // lo guardo en el campo
    }

    @GetMapping // responde a GET /api/tasks
    public List<Task> getAll() { // devuelve todas las tareas
        return repository.findAll(); // SELECT * FROM tasks, Spring lo convierte a JSON
    }

    @PostMapping // responde a POST /api/tasks
    @ResponseStatus(HttpStatus.CREATED) // devuelve 201 en vez de 200 porque se creó algo
    public Task create(@Valid @RequestBody Task task) { // @RequestBody convierte el JSON a Task y @Valid lo valida
        task.setId(null); // por si el front manda un id, lo borro para que MySQL cree uno nuevo y no pise otra tarea
        return repository.save(task); // INSERT, devuelve la tarea ya con su id para que el front la use
    }

    @PutMapping("/{id}") // responde a PUT /api/tasks/5, el 5 llega en la ruta
    public Task update(@PathVariable Long id, @Valid @RequestBody Task datos) { // el id sale de la ruta y los datos nuevos del JSON
        Task task = repository.findById(id) // busco la tarea en la base
                .orElseThrow(() -> new TaskNotFoundException(id)); // si no existe lanzo la excepción que termina en 404
        task.setNombre(datos.getNombre()); // actualizo el nombre
        task.setDescripcion(datos.getDescripcion()); // actualizo la descripción
        task.setAsignadoA(datos.getAsignadoA()); // actualizo el responsable
        task.setFechaDeEntrega(datos.getFechaDeEntrega()); // actualizo la fecha de entrega, con el nombre real del front
        task.setEstado(datos.getEstado()); // actualizo el estado, así se marca como HECHO
        task.setProyectoId(datos.getProyectoId()); // actualizo el proyecto
        return repository.save(task); // como ya tiene id, save hace UPDATE y no INSERT
    }

    @DeleteMapping("/{id}") // responde a DELETE /api/tasks/5
    @ResponseStatus(HttpStatus.NO_CONTENT) // devuelve 204: se borró y no hay nada que mostrar
    public void delete(@PathVariable Long id) { // recibe el id de la ruta
        if (!repository.existsById(id)) { // primero compruebo que exista
            throw new TaskNotFoundException(id); // si no existe, 404, porque deleteById no avisa nada
        }
        repository.deleteById(id); // DELETE FROM tasks WHERE id = ?
    }
}