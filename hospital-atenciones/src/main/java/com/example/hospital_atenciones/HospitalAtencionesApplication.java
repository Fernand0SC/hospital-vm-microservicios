package com.example.hospital_atenciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //se añade esta extension para poder implementar la comunicacion entre microservicios
@SpringBootApplication
public class HospitalAtencionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAtencionesApplication.class, args);
	}

}
