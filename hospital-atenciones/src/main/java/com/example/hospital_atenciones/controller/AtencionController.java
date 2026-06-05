package com.example.hospital_atenciones.controller;

import com.example.hospital_atenciones.model.Atencion;
import com.example.hospital_atenciones.client.AtencionDetalleDTO; // Importación correcta
import com.example.hospital_atenciones.service.AtencionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    private static final Logger log = LoggerFactory.getLogger(AtencionController.class);  //se agrega para la implementacion de logs


    @Autowired
    private AtencionService atencionService;

    @GetMapping
    public ResponseEntity<List<Atencion>> listarAtenciones() {
        List<Atencion> atenciones = atencionService.findAll();
        if (atenciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atenciones);
    }

    @PostMapping
    public ResponseEntity<Atencion> guardarAtencion(@Valid @RequestBody Atencion atencion){
        log.info("Creando nuevo paciente con RUN: {}", atencion.getId());  //linea necesaria para el log

        Atencion atencionNueva = atencionService.save(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(atencionNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> buscarAtencion(@PathVariable Long id) {
        try {
            Atencion atencion = atencionService.findById(id);
            if (atencion == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(atencion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Atencion>> listarPorPaciente(@PathVariable Long idPaciente) {
        List<Atencion> atenciones = atencionService.findByIdPaciente(idPaciente);
        if (atenciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atenciones);
    }

    // --- ENDPOINT ACTIVIDAD 3 ---
    @GetMapping("/{id}/detalle")
    public ResponseEntity<AtencionDetalleDTO> obtenerAtencionDetalle(@PathVariable Long id) {
        try {
            AtencionDetalleDTO detalle = atencionService.obtenerAtencionConPaciente(id);
            if (detalle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(detalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> actualizarAtencion(@PathVariable Long id,@Valid @RequestBody Atencion atencionNuevosDatos){
        try {
            Atencion atencionExistente = atencionService.findById(id);
            if (atencionExistente == null) {
                return ResponseEntity.notFound().build();
            }

            atencionExistente.setFechaAtencion(atencionNuevosDatos.getFechaAtencion());
            atencionExistente.setHoraAtencion(atencionNuevosDatos.getHoraAtencion());
            atencionExistente.setCosto(atencionNuevosDatos.getCosto());
            atencionExistente.setComentario(atencionNuevosDatos.getComentario());
            atencionExistente.setIdPaciente(atencionNuevosDatos.getIdPaciente());

            Atencion atencionActualizada = atencionService.save(atencionExistente);
            return ResponseEntity.ok(atencionActualizada);

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAtencion(@PathVariable Long id){
        try {
            atencionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}