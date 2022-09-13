package uk.co.alurachallengerbe.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;
import uk.co.alurachallengerbe.services.exceptions.DatabaseException;
import uk.co.alurachallengerbe.services.exceptions.ResourceNotFoundException;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public Page<Receita> findAll(Pageable paginacao){
		return repository.findAll(paginacao);
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public List<Receita> findByDescricao(String descricao) {
		return repository.findByDescricaoContaining(descricao);
	}
	
	public List<Receita> findByMonthAndYear(Integer year, Integer month) {
		return repository.findByDataGreaterThanEqualAndDataLessThan(
			LocalDate.of(year, month, 1), LocalDate.of(year, month + 1, 1));// ano, mes , dia
	}
	
	public Receita insert (Receita obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	public Receita update(Long id, Receita obj) {
		try {
			Receita entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Receita entity, Receita obj) {
		entity.setData(obj.getData());
		entity.setDescricao(obj.getDescricao());
		entity.setId(obj.getId());
		entity.setValor(obj.getValor());		
	}
			
}
