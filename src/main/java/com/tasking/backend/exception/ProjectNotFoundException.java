package com.tasking.backend.exception; // misma carpeta que TaskNotFoundException

public class ProjectNotFoundException extends RuntimeException { // excepcion propia para cuando un proyecto no existe
    public ProjectNotFoundException(Long id) { // recibe el id que no se encontro
        super("No existe un proyecto con id " + id); // mensaje que el front va a mostrar
    }
}