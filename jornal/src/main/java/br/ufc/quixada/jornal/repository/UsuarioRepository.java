package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.jornal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public List<Usuario> findByNomeCompletoContaining(String usuario);
	
	//public List<Usuario> findByLoginLike(String login);
	
	public Usuario findByLoginLike(String login);
	
} 
