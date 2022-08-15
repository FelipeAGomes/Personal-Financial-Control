package uk.co.alurachallengerbe.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.alurachallengerbe.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	
	List<Receita> findByDescricao(String descricao);
//	List<Receita> findByData(LocalDate data);
}
