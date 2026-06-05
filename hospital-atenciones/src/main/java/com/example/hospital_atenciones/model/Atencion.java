package com.example.hospital_atenciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "La fecha de atención es obligatoria")
    @PastOrPresent(message = "La fecha de atención no puede ser en el futuro")
    @Column(nullable = false)
    private LocalDate fechaAtencion;

    @NotNull(message = "La hora de atención es obligatoria")
    @Column(nullable = false)
    private LocalTime horaAtencion;

    @NotNull(message = "El costo de la atención es obligatorio")
    @PositiveOrZero(message = "El costo no puede ser negativo")
    @Column(nullable = false,length = 10)
    private BigDecimal costo;

    @Size(max = 255, message = "El comentario no puede exceder los 255 caracteres")
    @Column(nullable = true)
    private String comentario;

    @NotNull(message = "El ID del paciente es obligatorio")
    @Positive(message = "El ID del paciente debe ser un número positivo")
    @Column(nullable = false)
    private Long idPaciente;

    @NotNull(message = "El ID del médico es obligatorio")
    @Positive(message = "El ID del médico debe ser un número positivo")
    @Column(nullable = false)
    private Long idMedico;

}
