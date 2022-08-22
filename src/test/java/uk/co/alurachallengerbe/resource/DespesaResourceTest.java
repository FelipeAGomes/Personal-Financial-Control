package uk.co.alurachallengerbe.resource;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // para injetar o mock no teste
//@WebMvcTest // somente para controller
@ActiveProfiles("test")
public class DespesaResourceTest {

	@Autowired
	private MockMvc mockMvc; // modulo de teste do spring // Mock simula uma requisicao Mvc

	@Test
	public void devolverListaDespesa() throws Exception {

		URI uri = new URI("/despesas");
		String json = "{\"id\": 1,\n" + "    \"descricao\": \"teste\",\n" + "    \"valor\": 1500.0,\n"
				+ "    \"data\": \"27-12-2018\",\n" + "    \"categoria\": \"EDUCACAO\"}";
		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
						.status()
						.is(200));
	}
}
