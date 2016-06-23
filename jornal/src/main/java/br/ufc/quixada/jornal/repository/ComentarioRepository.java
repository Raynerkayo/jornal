package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Comentario;
import br.ufc.quixada.jornal.model.Noticia;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	@Query("select c from Comentario c where c.usuario = ?")
	List<Comentario> findByComentarioIdUsuario(Long usuario); 
 
	@Query("select c from Comentario c where c.noticia = ?")
	List<Comentario> findByComentarioIdNoticia(Long idNoticia);

	//@Query("select u.login, c.texto, n.texto, n.data_noticia from comentario c, noticia n, usuario u where (c.id_noticia = ? and n.id = ?) and (c.id_usuario = ?  and u.id = ?)", nativeQuery = true)
	//List<Comentario> findByComentarioUsuarioNoticia(Long id, Long idS);
	List<Comentario> findByUsuarioAndNoticiaContains(Long usuario, Long noticia);
	
	List<Comentario> findByNoticiaLike(Noticia id);
}
