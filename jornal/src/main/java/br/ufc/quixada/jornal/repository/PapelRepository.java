package br.ufc.quixada.jornal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	public List<Papel> findByPapelNomeContaining(String papel);

	/*
	 * select u.nome_completo, p.papel_nome, u.id from usuario u, papel p,
	 * papel_usuario pu where pu.id_papel = 2 and (p.id_papel = pu.id_papel and
	 * u.id = pu.id_usuario); // Query nativa para trazer o papel do usuário
	 */
	@Query(value = "SELECT ", nativeQuery = true)
	List<Papel> paIdPapeçIdUsuario(Long idUsuario);
}
