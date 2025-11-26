package com.northpole.auditoria_elfos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableEnversRepositories
public class ProjetoNoelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoNoelApplication.class, args);
	}

}
