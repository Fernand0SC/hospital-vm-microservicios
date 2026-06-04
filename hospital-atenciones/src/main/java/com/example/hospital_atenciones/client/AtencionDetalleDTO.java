package com.example.hospital_atenciones.client;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AtencionDetalleDTO {
    private Long id;
    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;
    private BigDecimal costo;
    private String comentario;
    private PacienteDTO paciente;   //se agrega para que en la consulta de postman aparaezcan sus datos
    private MedicoDTO medico;   //se agrega para que en la consulta de postman aparaezcan sus datos
}
