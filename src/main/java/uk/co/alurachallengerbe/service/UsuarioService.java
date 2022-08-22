package uk.co.alurachallengerbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.alurachallengerbe.entities.Usuario;
import uk.co.alurachallengerbe.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Optional<Usuario> findByIdReceitas(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj;
	}
	
	public Optional<Usuario> findByIdDespesas(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj;
	}
}
