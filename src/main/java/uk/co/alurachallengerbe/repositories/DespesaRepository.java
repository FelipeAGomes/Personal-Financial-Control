package uk.co.alurachallengerbe.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.alurachallengerbe.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
		
	Page<Despesa> findAll(Pageable paginacao);
	
	List<Despesa> findByDescricao(String descricao);
	
	List<Despesa> findByDataGreaterThanEqualAndDataLessThan(
			LocalDate of, LocalDate of2);
}
