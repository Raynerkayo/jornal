package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.repositoy.PapelRepository;

@Service
public class PapelService {

	@Autowired
	private PapelRepository papelRepository;

	public void salvar(Papel papel) {
		papelRepository.save(papel);
	}

	public List<Papel> listar() {
		List<Papel> papeis = papelRepository.findAll();
		return papeis;
	}

	public void excluir(Long id) {
		papelRepository.delete(id);		
	}

}
