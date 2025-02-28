package com.apptt2.backend;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
        ZonedDateTime fechaMexico = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
        System.out.println("Fecha y hora en CDMX: " + fechaMexico);
		SpringApplication.run(BackendApplication.class, args);
	}

    @PostConstruct
    public void init() {
        // Esto asegura que toda la aplicación use la zona horaria correcta
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("America/Mexico_City")));
    }
}
