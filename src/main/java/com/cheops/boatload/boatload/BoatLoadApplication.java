package com.cheops.boatload.boatload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.cheops.kontich.boatload.Main;

@SpringBootApplication
public class BoatLoadApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BoatLoadApplication.class, args);

		Main.execute();
	}

}
