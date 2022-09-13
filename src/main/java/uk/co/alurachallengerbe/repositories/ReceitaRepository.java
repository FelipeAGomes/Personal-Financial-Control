package uk.co.alurachallengerbe.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.alurachallengerbe.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	
	Page<Receita> findAll(Pageable paginacao);
	
	List<Receita> findByDescricaoContaining(String descricao);
	
	List<Receita> findByDataGreaterThanEqualAndDataLessThan(
		LocalDate of, LocalDate of2);
}
