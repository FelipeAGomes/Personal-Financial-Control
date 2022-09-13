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

import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.entities.enums.Categoria;
import uk.co.alurachallengerbe.repositories.DespesaRepository;
import uk.co.alurachallengerbe.services.exceptions.DatabaseException;
import uk.co.alurachallengerbe.services.exceptions.ResourceNotFoundException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;

	public Page<Despesa> findAll(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Despesa> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}

	public List<Despesa> findByMonthAndYear(Integer year, Integer month) {
		return repository.findByDataGreaterThanEqualAndDataLessThan(LocalDate.of(year, month, 1),
				LocalDate.of(year, month + 1, 1));// ano, mes , dia
	}

	public Despesa insert(Despesa obj) throws IllegalAccessException {
		if (obj.getCategoria() == null) {
			obj.setCategoria(Categoria.OUTRAS);
		}
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
	public Despesa update(Long id, Despesa obj) {
		try {
			Despesa entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Despesa entity, Despesa obj) {
		entity.setData(obj.getData());
		entity.setDescricao(obj.getDescricao());
		entity.setId(obj.getId());
		entity.setValor(obj.getValor());
	}
}
