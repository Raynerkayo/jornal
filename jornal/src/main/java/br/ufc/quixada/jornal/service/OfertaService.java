package br.ufc.quixada.jornal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Oferta;
import br.ufc.quixada.jornal.repository.OfertaRepository;

@Service
public class OfertaService{
	
	@Autowired
	private OfertaRepository ofertaRepository; 

	public void salvar(Oferta oferta){
		ofertaRepository.save(oferta);
	}

	public Oferta valorOfertasAnterior() {
		return ofertaRepository.valorOfertasAnterior();
	}
	
	
	
}
