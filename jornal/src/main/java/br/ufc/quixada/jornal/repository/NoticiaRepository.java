package br.ufc.quixada.jornal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.jornal.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	
}
