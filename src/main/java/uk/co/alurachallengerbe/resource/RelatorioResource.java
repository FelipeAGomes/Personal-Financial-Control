package uk.co.alurachallengerbe.resource;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<RelatorioDTO> getRelatorio(@PathVariable Integer year, @PathVariable Integer month) {
		RelatorioDTO relatorio = service.getRelatorio(year, month);
		return ResponseEntity.ok().body(relatorio);
	}
}
