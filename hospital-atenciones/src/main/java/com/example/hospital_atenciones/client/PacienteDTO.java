package com.example.hospital_atenciones.client;

import lombok.Data;

import java.util.Date;

@Data
public class PacienteDTO {

    private Long id;
    private String run;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String correo;

}
