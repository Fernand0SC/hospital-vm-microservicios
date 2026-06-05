package com.example.hospital_medico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El rut no puede estar vacio")   //esto es Validaciones con Bean Validation
    @Size(min = 9, max = 12, message = "El RUT debe tener entre 9 y 12 caracteres")
    @Column(unique = true, length = 13, nullable = false)
    private String run;

    @NotBlank(message = "Los nombres no pueden estar vacios")
    @Column(nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacios")
    @Column(nullable = false)
    private String apellidos;

    @NotBlank(message = "La especialidad no puede estar vacia")
    @Column(nullable = false)
    private String especialidad;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe tener un formato de correo válido")
    @Column(nullable = false)
    private String email;



}
