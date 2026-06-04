package com.example.hospital_medico.controller;

import com.example.hospital_medico.model.Medico;
import com.example.hospital_medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos(){
        List<Medico> medicos = medicoService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> guardarMedico(@RequestBody Medico medico){
        Medico medicoNuevo = medicoService.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable Long id) {
        try {
            Medico medico = medicoService.findById(id);
            return ResponseEntity.ok(medico);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            // Buscamos directamente el medico (asumiendo que si no existe, el servicio retorna null)
            Medico medicoExistente = medicoService.findById(id);

            if (medicoExistente != null) {
                // Pasamos los datos nuevos al objeto encontrado
                medicoExistente.setRun(medico.getRun());
                medicoExistente.setNombres(medico.getNombres());
                medicoExistente.setApellidos(medico.getApellidos());
                medicoExistente.setEspecialidad(medico.getEspecialidad());
                medicoExistente.setEmail(medico.getEmail());

                // Guardamos los cambios
                Medico medicoActualizado = medicoService.save(medicoExistente);
                return ResponseEntity.ok(medicoActualizado);
            } else {
                // Si retornó null, es porque no existía en la BD
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id){
        try {
            medicoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
