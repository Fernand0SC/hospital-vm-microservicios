package com.example.hospital_vm.controller;

import com.example.hospital_vm.model.Paciente;
import com.example.hospital_vm.service.PacienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);  //se agrega para la implementacion de logs

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@Valid @RequestBody Paciente paciente){
        log.info("Creando nuevo paciente con RUN: {}", paciente.getRun());  //linea necesaria para el log
        Paciente pacienteNuevo = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.findById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id,@Valid @RequestBody Paciente paciente) {
        try {
            // Buscamos directamente el paciente (asumiendo que si no existe, el servicio retorna null)
            Paciente pacienteExistente = pacienteService.findById(id);

            if (pacienteExistente != null) {
                // Pasamos los datos nuevos al objeto encontrado
                pacienteExistente.setRun(paciente.getRun());
                pacienteExistente.setNombres(paciente.getNombres());
                pacienteExistente.setApellidos(paciente.getApellidos());
                pacienteExistente.setFechaNacimiento(paciente.getFechaNacimiento());
                pacienteExistente.setCorreo(paciente.getCorreo());

                // Guardamos los cambios
                Paciente pacienteActualizado = pacienteService.save(pacienteExistente);
                return ResponseEntity.ok(pacienteActualizado);
            } else {
                // Si retornó null, es porque no existía en la BD
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> eliminarPaciente(@PathVariable Long id){
        try {
            pacienteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
