package uk.co.alurachallengerbe.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.service.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaResource {

	@Autowired ReceitaService service;
	
	@GetMapping
	public ResponseEntity<List<Receita>> findAll(){
		List<Receita> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Receita> findById(@PathVariable Long id){
		Receita obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/descricao/{descricao}")
	public List<Receita> findByDescricao(@PathVariable("descricao") String descricao){
		return service.findByDescricao(descricao);
	}
	
//	@GetMapping(value = "/data/{data}")
//	public List<Receita> findByData(@PathVariable("data") LocalDate data){
//		return findByData(data);
//	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Receita> update(@PathVariable Long id, @RequestBody Receita obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Receita> insert(@RequestBody Receita obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
