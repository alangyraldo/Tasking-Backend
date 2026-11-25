package com.tasking.backend.exception; // carpeta de las excepciones

public class TaskNotFoundException extends RuntimeException { // excepción propia para cuando una tarea no existe
    public TaskNotFoundException(Long id) { // recibe el id que no se encontró
        super("No existe una tarea con id " + id); // le pasa el mensaje a RuntimeException, el front lo va a mostrar
    }
}