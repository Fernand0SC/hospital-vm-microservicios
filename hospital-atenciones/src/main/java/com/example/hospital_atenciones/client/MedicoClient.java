package com.example.hospital_atenciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hospital-medico", url = "http://localhost:8083/api/v1/medicos")

public interface MedicoClient {
    @GetMapping("/{id}")
    MedicoDTO obtenerMedicoPorId(@PathVariable("id") Long id);
}
