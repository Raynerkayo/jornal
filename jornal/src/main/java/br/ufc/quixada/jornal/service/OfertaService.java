package br.ufc.quixada.jornal.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.model.Oferta;
import br.ufc.quixada.jornal.repository.OfertaRepository;

@Service
public class OfertaService{
	
	@Autowired
	private OfertaRepository ofertaRepository; 

	public void salvar(Oferta oferta){
		ofertaRepository.save(oferta);
	}
	
	public List<Oferta> listar(){
		return ofertaRepository.findAll();
	}
	
	public Oferta listar(Classificado id){
		return ofertaRepository.findByClassificadoLike(id);
	}

	public void remover(Long melhorOferta) {
		ofertaRepository.delete(melhorOferta);
	}
	
	
}
