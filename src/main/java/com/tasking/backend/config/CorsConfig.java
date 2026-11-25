package com.tasking.backend.config; // carpeta de configuraciones

import org.springframework.context.annotation.Configuration; // marca la clase como configuración
import org.springframework.web.servlet.config.annotation.CorsRegistry; // donde se registran las reglas de CORS
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // permite personalizar Spring MVC

@Configuration // Spring la carga solo al arrancar
public class CorsConfig implements WebMvcConfigurer { // implemento la interfaz para poder agregar reglas

    @Override // sobrescribo el método que define CORS
    public void addCorsMappings(CorsRegistry registry) { // aquí digo quién puede llamar a mi API
        registry.addMapping("/api/**") // la regla vale para todas las rutas que empiezan por /api
                .allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*") // cualquier puerto de localhost, así Live Server puede usar 5500, 5501, 5502...
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // OPTIONS lo manda el navegador antes de POST y PUT para preguntar si puede
                .allowedHeaders("*"); // acepta cualquier encabezado, como Content-Type: application/json
    }
}