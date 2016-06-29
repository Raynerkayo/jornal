package br.ufc.quixada.jornal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quixada.jornal.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

	@Query(value = "select MAX(o.valorOferta), o.usuario, o.dataOferta from Oferta o")
	Oferta valorOfertasAnterior();
}
