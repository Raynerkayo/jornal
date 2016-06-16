package br.ufc.quixada.jornal.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.jornal.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{
	public List<Papel> findByPapelNomeContaining(String papel);
}
