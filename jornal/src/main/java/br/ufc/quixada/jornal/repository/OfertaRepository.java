package br.ufc.quixada.jornal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

	Oferta findByClassificadoLike(Classificado id);

}
