package com.example.hospital_atenciones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "atencion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaAtencion;


    @Column(nullable = false)
    private LocalTime horaAtencion;


    @Column(nullable = false,length = 10)
    private BigDecimal costo;

    @Column(nullable = true)
    private String comentario;


    @Column(nullable = false)
    private Long idPaciente;

    @Column(nullable = false)
    private Long idMedico;

}
