package uk.co.alurachallengerbe.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.entities.Usuario;
import uk.co.alurachallengerbe.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/receitas/{id}")
	@Cacheable(value = "listaReceitasUsuarioId")
	public ResponseEntity<List<Receita>> findByIdReceitas(@PathVariable("id")Long id){
		List<Receita> obj = service.findByIdReceitas(id).get().getReceitas();
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/despesas/{id}")
	@Cacheable(value = "listaDespesasUsuarioId")
	public ResponseEntity<List<Despesa>> findByIdDespesas(@PathVariable("id")Long id){
		List<Despesa> obj = service.findByIdDespesas(id).get().getDespesas();
		return ResponseEntity.ok().body(obj);
	}
}
