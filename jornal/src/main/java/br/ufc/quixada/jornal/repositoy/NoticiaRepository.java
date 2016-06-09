package br.ufc.quixada.jornal.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.jornal.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	
}
