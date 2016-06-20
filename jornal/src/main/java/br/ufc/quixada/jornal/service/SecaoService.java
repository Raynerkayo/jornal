package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Secao;
import br.ufc.quixada.jornal.repository.SecaoRepository;

@Service
public class SecaoService{
	
	@Autowired
	private SecaoRepository secaoRepository;
	
	public void salvar(Secao secao){
		secaoRepository.save(secao);
	}

	public List<Secao> listar() {
		List<Secao> secoes = secaoRepository.findAll();
		return secoes;
	}
	
	public void excluir(Long id){
		secaoRepository.delete(id);
	}
	
}
