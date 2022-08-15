package uk.co.alurachallengerbe.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.entities.enums.Categoria;
import uk.co.alurachallengerbe.repositories.DespesaRepository;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Receita r1 = new Receita(null, "teste", (double) 1500, LocalDate.parse("2018-11-27"));
		Receita r2 = new Receita(null, "teste", (double) 15, LocalDate.parse("2018-10-22"));
		Receita r3 = new Receita(null, "teste2", (double) 157, LocalDate.parse("2018-10-25"));
		Receita r4 = new Receita(null, "teste2", (double) 875, LocalDate.parse("2018-09-26"));
		Receita r5 = new Receita(null, "teste3", (double) 780, LocalDate.parse("2018-05-17"));
		Receita r6 = new Receita(null, "teste4", (double) 570, LocalDate.parse("2018-02-15"));
		Receita r7 = new Receita(null, "teste4", (double) 680, LocalDate.parse("2018-03-12"));
		
		
		receitaRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7));
		
		Despesa d1 = new Despesa(null, "teste", (double) 1500, LocalDate.parse("2018-12-27"), Categoria.EDUCACAO);
		Despesa d2 = new Despesa(null, "teste", (double) 15, LocalDate.parse("2018-10-22"), Categoria.ALIMENTACAO);
		Despesa d3 = new Despesa(null, "teste2", (double) 157, LocalDate.parse("2018-10-25"), Categoria.IMPREVISTOS);
		Despesa d4 = new Despesa(null, "teste2", (double) 875, LocalDate.parse("2018-09-26"), Categoria.LAZER);
		Despesa d5 = new Despesa(null, "teste3", (double) 780, LocalDate.parse("2018-05-17"), Categoria.MORADIA);
		Despesa d6 = new Despesa(null, "teste4", (double) 570, LocalDate.parse("2018-02-15"), Categoria.SAUDE);
		Despesa d7 = new Despesa(null, "teste4", (double) 680, LocalDate.parse("2018-03-12"), Categoria.TRANSPORTE);
		
		despesaRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7));
	}
	
}
