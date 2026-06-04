package com.example.hospital_atenciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hospital-vm", url = "http://localhost:8080/api/v1/pacientes")
public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteDTO obtenerPacientePorId(@PathVariable("id") Long id);
}