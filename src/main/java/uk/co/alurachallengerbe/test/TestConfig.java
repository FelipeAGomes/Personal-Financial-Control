package uk.co.alurachallengerbe.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.entities.Usuario;
import uk.co.alurachallengerbe.entities.enums.Categoria;
import uk.co.alurachallengerbe.repositories.DespesaRepository;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;
import uk.co.alurachallengerbe.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Usuario u1 = new Usuario(null, "Felipe", "Gomes", "1234", "felipe@gmail.com");

		usuarioRepository.saveAll(Arrays.asList(u1));

		Receita r1 = new Receita(null, "teste", (double) 1500, LocalDate.parse("2018-11-27"), u1);
		Receita r2 = new Receita(null, "teste", (double) 15, LocalDate.parse("2018-10-22"), u1);
		Receita r3 = new Receita(null, "teste2", (double) 157, LocalDate.parse("2018-10-25"), u1);
		Receita r4 = new Receita(null, "teste2", (double) 875, LocalDate.parse("2018-09-26"), u1);
		Receita r5 = new Receita(null, "teste3", (double) 780, LocalDate.parse("2018-05-17"), u1);
		Receita r6 = new Receita(null, "teste4", (double) 570, LocalDate.parse("2018-02-15"), u1);
		Receita r7 = new Receita(null, "teste4", (double) 680, LocalDate.parse("2018-03-12"), u1);

		receitaRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7));

		Despesa d1 = new Despesa(null, "teste", (double) 1500, LocalDate.parse("2018-12-27"), Categoria.EDUCACAO, u1);
		Despesa d2 = new Despesa(null, "teste", (double) 15, LocalDate.parse("2018-10-22"), Categoria.ALIMENTACAO, u1);
		Despesa d3 = new Despesa(null, "teste2", (double) 157, LocalDate.parse("2018-10-25"), Categoria.IMPREVISTOS,
				u1);
		Despesa d4 = new Despesa(null, "teste2", (double) 875, LocalDate.parse("2018-09-26"), Categoria.LAZER, u1);
		Despesa d5 = new Despesa(null, "teste3", (double) 780, LocalDate.parse("2018-05-17"), Categoria.MORADIA, u1);
		Despesa d6 = new Despesa(null, "teste4", (double) 570, LocalDate.parse("2018-02-15"), Categoria.SAUDE, u1);
		Despesa d7 = new Despesa(null, "teste4", (double) 680, LocalDate.parse("2018-03-12"), Categoria.TRANSPORTE, u1);

		despesaRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7));

	}

}
