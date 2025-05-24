package br.com.digimon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.digimon")
@EnableCaching
public class DigimonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigimonApplication.class, args);
	}

}
