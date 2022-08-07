package uk.co.alurachallengerbe.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;
import uk.co.alurachallengerbe.services.exceptions.DatabaseException;
import uk.co.alurachallengerbe.services.exceptions.ResourceNotFoundException;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public List<Receita> findAll(){
		return repository.findAll();
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
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
