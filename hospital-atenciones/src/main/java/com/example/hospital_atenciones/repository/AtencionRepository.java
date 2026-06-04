package com.example.hospital_atenciones.repository;

import com.example.hospital_atenciones.model.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    // Metodo personalizado para buscar todas las atenciones que le pertenecen a un paciente específico
    List<Atencion> findByIdPaciente(Long idPaciente);
}
