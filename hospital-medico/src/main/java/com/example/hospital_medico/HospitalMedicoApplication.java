package com.example.hospital_medico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HospitalMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalMedicoApplication.class, args);
	}

}
