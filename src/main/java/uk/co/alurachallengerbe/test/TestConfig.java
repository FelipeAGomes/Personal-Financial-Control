package uk.co.alurachallengerbe.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Receita r1 = new Receita(null, "teste", (double) 1500, Instant.parse("2019-06-20T19:53:07Z"));
		
		receitaRepository.saveAll(Arrays.asList(r1));
	}
	
}
