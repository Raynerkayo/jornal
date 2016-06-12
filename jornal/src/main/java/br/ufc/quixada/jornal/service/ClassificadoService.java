package br.ufc.quixada.jornal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.repository.ClassificadoRepository;

@Service
public class ClassificadoService {

	@Autowired
	private ClassificadoRepository classificadoRepository;
	
	public void salvar(Classificado classificado){
		classificadoRepository.save(classificado);
	}
	
	
	
}
