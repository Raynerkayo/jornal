package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	public List<Papel> findByPapelNomeContaining(String papel);

	/*@Query(value = "select pu.*, p.* from papel_usuario pu, papel p where id_usuario = ?", nativeQuery = true)
	Papel findByPapelIdUsuario(Long idUsuario);*/
	@Query(value = "select pu.*, p.* from papel_usuario pu, papel p where pu.id_usuario = ?", nativeQuery = true)
	Papel findByPapelIdUsuario(Long id);
	
}
