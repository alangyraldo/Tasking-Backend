package com.tasking.backend.exception; // misma carpeta que la excepción propia

import org.springframework.http.HttpStatus; // códigos HTTP
import org.springframework.web.bind.MethodArgumentNotValidException; // la que lanza Spring cuando falla un @Valid
import org.springframework.web.bind.annotation.ExceptionHandler; // indica qué excepción atiende cada método
import org.springframework.web.bind.annotation.ResponseStatus; // fija el código de respuesta
import org.springframework.web.bind.annotation.RestControllerAdvice; // un manejador global para todos los controladores
import java.util.HashMap; // mapa mutable para juntar los errores
import java.util.Map; // tipo del JSON que se devuelve

@RestControllerAdvice // atrapa las excepciones de todos los controladores y devuelve JSON
public class GlobalExceptionHandler { // un solo lugar para los errores, así no repito try-catch en cada método

    @ExceptionHandler(MethodArgumentNotValidException.class) // se activa cuando falla una validación
    @ResponseStatus(HttpStatus.BAD_REQUEST) // responde 400
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) { // recibe la excepción con todos los fallos
        Map<String, String> errores = new HashMap<>(); // aquí junto campo → mensaje
        ex.getBindingResult().getFieldErrors() // saco la lista de campos que fallaron
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage())); // guardo, por ejemplo, "nombre" → "El nombre es obligatorio"
        return errores; // el front lo recibe como JSON y puede mostrarlo debajo de cada input
    }

    @ExceptionHandler(TaskNotFoundException.class) // se activa cuando la tarea no existe
    @ResponseStatus(HttpStatus.NOT_FOUND) // responde 404
    public Map<String, String> handleNotFound(TaskNotFoundException ex) { // recibe la excepción que lancé en el controlador
        return Map.of("error", ex.getMessage()); // devuelve {"error": "No existe una tarea con id 5"}
    }

    @ExceptionHandler(ProjectNotFoundException.class) // se activa cuando un proyecto no existe
    @ResponseStatus(HttpStatus.NOT_FOUND) // responde 404
    public Map<String, String> handleProjectNotFound(ProjectNotFoundException ex) { // recibe la excepcion que lance en el controlador
        return Map.of("error", ex.getMessage()); // devuelve {"error": "No existe un proyecto con id 5"}
    }
}