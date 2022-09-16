package uk.co.alurachallengerbe.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Cacheable(value = "listaReceitas")
	public ResponseEntity<Page<Receita>> findAll(Pageable paginacao){		
		Page<Receita> list = service.findAll(paginacao);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@Cacheable(value = "receitasId") // para salvar o cache e diminuir o acesso ao db
	public ResponseEntity<Receita> findById(@PathVariable("id") Long id){
		Receita obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/descricao/{descricao}")
	@Cacheable(value = "listaReceitaDescricao")
	public List<Receita> findByDescricao(@PathVariable("descricao") String descricao){
		return service.findByDescricao(descricao);
	}
	
	@GetMapping(value = "data/{year}/{month}")
	@Cacheable(value = "listaReceitasByMonth")
	public ResponseEntity<List<Receita>> findDataByMonth(@PathVariable Integer year,
			@PathVariable Integer month) {
		List<Receita> list = service.findByMonthAndYear(year, month);
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = "receitasId, listaReceitaDescricao, listaReceitasByMonth, listaReceitasUsuarioId, relatorio", allEntries = true) // para limpar o cache em caso da alguma exclusao no db
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	@CacheEvict(value = "receitasId, listaReceitaDescricao, listaReceitasByMonth, listaReceitasUsuarioId, relatorio", allEntries = true)
	public ResponseEntity<Receita> update(@PathVariable Long id, @RequestBody Receita obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@CacheEvict(value = "receitasId, listaReceitaDescricao, listaReceitasByMonth, listaReceitasUsuarioId, relatorio", allEntries = true)
	public ResponseEntity<Receita> insert(@RequestBody Receita obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
