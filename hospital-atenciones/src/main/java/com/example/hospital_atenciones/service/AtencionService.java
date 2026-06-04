package com.example.hospital_atenciones.service;

import com.example.hospital_atenciones.client.*;
import com.example.hospital_atenciones.model.Atencion;
import com.example.hospital_atenciones.repository.AtencionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private PacienteClient pacienteClient; // El puente de red

    @Autowired
    private MedicoClient medicoClient;

    public List<Atencion> findAll() {
        return atencionRepository.findAll();
    }

    public Atencion findById(Long id) {
        return atencionRepository.findById(id).get();
    }

    public List<Atencion> findByIdPaciente(Long idPaciente) {
        return atencionRepository.findByIdPaciente(idPaciente);
    }

    public Atencion save(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public void delete(Long id) {
        atencionRepository.deleteById(id);
    }

    // --- METODO DE LA ACTIVIDAD 3 PACIENTES Y MEDICO---
    public AtencionDetalleDTO obtenerAtencionConPaciente(Long idAtencion) {
        Atencion atencion = atencionRepository.findById(idAtencion).orElse(null);
        if (atencion == null) {
            return null;
        }

        PacienteDTO pacienteDTO = null;
        try {
            pacienteDTO = pacienteClient.obtenerPacientePorId(atencion.getIdPaciente());
        } catch (Exception e) {
            System.out.println("Error conectando a Pacientes: " + e.getMessage());
        }

        MedicoDTO medicoDTO = null;
        try {
            medicoDTO = medicoClient.obtenerMedicoPorId(atencion.getIdMedico());
        } catch (Exception e) {
            System.out.println("Error conectando a Medicos: " + e.getMessage());
        }

        AtencionDetalleDTO detalle = new AtencionDetalleDTO();
        detalle.setId(atencion.getId());
        detalle.setFechaAtencion(atencion.getFechaAtencion());
        detalle.setHoraAtencion(atencion.getHoraAtencion());
        detalle.setCosto(atencion.getCosto());
        detalle.setComentario(atencion.getComentario());
        detalle.setPaciente(pacienteDTO);
        detalle.setMedico(medicoDTO);

        return detalle;
    }
}