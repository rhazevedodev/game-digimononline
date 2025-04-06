package br.com.digimon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.digimon")
public class DigimonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigimonApplication.class, args);
	}

}
