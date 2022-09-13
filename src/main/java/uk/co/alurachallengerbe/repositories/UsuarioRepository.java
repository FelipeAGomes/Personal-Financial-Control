package uk.co.alurachallengerbe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.alurachallengerbe.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findById(Long id);
}
