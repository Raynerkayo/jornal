package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	@Query("select c from Comentario c where c.usuario = ?")
	List<Comentario> findByComentarioIdUsuario(Long usuario); 
 
	@Query("select c from Comentario c where c.noticia = ?")
	List<Comentario> findByComentarioIdNoticia(Long idNoticia);

}
