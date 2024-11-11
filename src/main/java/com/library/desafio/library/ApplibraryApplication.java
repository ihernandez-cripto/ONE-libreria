package com.library.desafio.library;


import com.library.desafio.library.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
ApplibraryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApplibraryApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
