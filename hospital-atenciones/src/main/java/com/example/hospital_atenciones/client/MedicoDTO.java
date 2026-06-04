package com.example.hospital_atenciones.client;

import lombok.Data;

@Data
public class MedicoDTO {

    private Long id;

    private String run;

    private String nombres;

    private String apellidos;

    private String especialidad;

    private String email;


}
