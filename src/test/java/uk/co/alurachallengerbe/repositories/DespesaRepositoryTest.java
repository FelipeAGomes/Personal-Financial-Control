package uk.co.alurachallengerbe.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import uk.co.alurachallengerbe.entities.Despesa;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // para nao substituir as configuracoes do banco
//@ActiveProfiles("test") // para ativar o perfil do banco em modo teste
public class DespesaRepositoryTest {
	
	@Autowired
	private DespesaRepository repository;
	
	@Autowired
	private TestEntityManager em;
		
	@Test
	public void test1() {
		
		String nomeDescricao="teste";
		
		Despesa teste = new Despesa();
		teste.setDescricao(nomeDescricao);
		em.persist(teste);
				
		List<Despesa> teste1 = repository.findByDescricao(nomeDescricao);
		
		Assert.assertNotNull(teste1);
		Assert.assertEquals(nomeDescricao, ((Despesa) teste1).getDescricao());
	}

}
