package com.northpole.auditoria_elfos;

import java.util.List;

import com.northpole.auditoria_elfos.entity.Crianca;
import com.northpole.auditoria_elfos.service.AuditoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
		repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
public class ProjetoNoelApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetoNoelApplication.class, args);
	}

	@Bean
	public CommandLineRunner inicializarDados(AuditoriaService service) {
		return (args) -> {
			System.out.println("\ud83c\udf85 --- POPULANDO A LISTA DE CRIANÇAS (8 REGISTROS) --- \ud83d\udcdd");
			Crianca felipe = new Crianca("Gael", "Playstations 5", false);
			Crianca monica = new Crianca("Francisca", "Tablet", true);
			Crianca pablo = new Crianca("Pablo", "Cama Para Dormir", true);
			Crianca joao = new Crianca("João", "Paz Na Terra", false);
			Crianca maria = new Crianca("Maria", "Bicicleta", false);
			Crianca ana = new Crianca("Ana", "Unicornio Verde", true);
			Crianca jose = new Crianca("José", "Material Escolar", true);
			Crianca pedro = new Crianca("Pedro", "Carrinho de controle Remoto", false);
			List<Crianca> criancasParaSalvar = List.of(ana, felipe, monica, pablo, joao, maria, jose, pedro);
			int qtdCriancas = service.salvarTodas(criancasParaSalvar);
			System.out.println("✅ Lista populada com sucesso. Total de registros: " + qtdCriancas);
			System.out.println("API REST JÁ DISPONÍVEL em http://localhost:8090/swagger-ui/index.html");
		};
	}
}

