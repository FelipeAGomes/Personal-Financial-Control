package uk.co.alurachallengerbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.alurachallengerbe.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
		
	List<Despesa> findByDescricao(String descricao);
}
