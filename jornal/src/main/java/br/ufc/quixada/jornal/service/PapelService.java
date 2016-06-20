package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.repository.PapelRepository;

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

	public Papel recupera(Long id) {
		Papel papel = papelRepository.findOne(id);
		return papel;
	}
	
	/*public Papel papelUsuario(Long idUsuario){
		//Papel papeis = papelRepository.findByPapelIdUsuario(idUsuario);
		return papel;
	}*/

}
