package uk.co.alurachallengerbe.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.service.DespesaService;

@RestController
@RequestMapping(value = "/despesas")
@Profile(value = {"prod", "test"})
public class DespesaResource {

	@Autowired
	private DespesaService service;

	@GetMapping
	public ResponseEntity<Page<Despesa>> findAll(Pageable paginacao) {		
		Page<Despesa> list = service.findAll(paginacao);		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable("id") Long id) {
		Despesa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/descricao/{descricao}")
	public List<Despesa> findByDescricao(@PathVariable("descricao") String descricao) {
		return service.findByDescricao(descricao);
	}

	@GetMapping(value = "data/{year}/{month}")
	public ResponseEntity<List<Despesa>> findDataByMonth(@PathVariable Integer year, @PathVariable Integer month) {
		List<Despesa> list = service.findByMonthAndYear(year, month);
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Despesa> insert(@RequestBody Despesa obj) throws IllegalAccessException {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
