package com.example.hospital_medico.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    // 1. Atrapa los errores de tus anotaciones @Valid (ej: dejar un campo vacío o enviar un número negativo)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }

    // 2. Atrapa los errores de Postman cuando mandas letras en la URL en vez de un ID numérico
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> manejarErrorDeTipo(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();

        error.put("mensaje", "Dato incorrecto en la URL. Asegúrate de enviar un número de ID válido.");

        return ResponseEntity.badRequest().body(error);
    }

}
