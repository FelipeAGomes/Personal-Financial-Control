package uk.co.alurachallengerbe.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.alurachallengerbe.dto.RelatorioDTO;
import uk.co.alurachallengerbe.service.RelatorioService;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioResource {

	@Autowired
	private RelatorioService service;

	@GetMapping(value = "/{year}/{month}")
	@Cacheable("relatorio")
	public ResponseEntity<RelatorioDTO> getRelatorio(@PathVariable Integer year, @PathVariable Integer month, Pageable paginacao) {
		RelatorioDTO relatorio = service.getRelatorio(year, month, paginacao);
		return ResponseEntity.ok().body(relatorio);
	}
}
