package com.example.hospital_vm.repository;

import com.example.hospital_vm.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Usando JPQL
    @Query("SELECT p FROM Paciente  p Where p.apellidos= :apellidos")
    List<Paciente> buscarPorApellidos(@Param("apellidos") String apellidos);

    //Usando SQL nativo
    @Query(value = "SELECT * FROM paciente where correo = :correo", nativeQuery = true)
    Paciente buscarPorCorreo(@Param("correo") String correo);

}
