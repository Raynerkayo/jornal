package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	public void salvar(Noticia noticia){
		noticiaRepository.save(noticia);
	}
	
	public void excluir(Long id){
		noticiaRepository.delete(id);
	}
	
	public List<Noticia> listar(){
		List<Noticia> noticias = noticiaRepository.findAll();
		return noticias;
	}
	
}
