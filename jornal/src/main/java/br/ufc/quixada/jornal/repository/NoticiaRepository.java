package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

	@Query(value = "SELECT * FROM noticia WHERE id_usuario = ?", nativeQuery = true)
	//@Query(value = "select n from Noticia n where n.usuario = ?") tenho que enviar um id do tipo usuário.
	List<Noticia> findByNoticiaIdUsuario(Long id);
	
	@Query(value = "SELECT * FROM noticia WHERE id_secao = ?", nativeQuery = true)
	List<Noticia> findByNoticiaSecao(Long id);
	
	Noticia findByIdLike(Long id);
	
/*	select p.papel_nome 
	from usuario u, papel p, papel_usuario pu 
	where u.id = 12 and (pu.id_usuario = u.id and pu.id_papel = p.id_papel);
*/

} 
