package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.model.Usuario;
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
	
	public List<Noticia> listarNoticiasJornalista(Long id){
		List<Noticia> noticiasJornalista = noticiaRepository.findByNoticiaIdUsuario(id);
		return noticiasJornalista;
	}
	
	public List<Noticia> listarNoticiaSecao(Long id){
		List<Noticia> noticiasSecao = noticiaRepository.findByNoticiaSecao(id);
		return noticiasSecao;
	}
	
	public Noticia buscarNoticia(Long id){
		return noticiaRepository.findByIdLike(id);
	}
	
	public Noticia buscarNoticiaPorIdNoticia(Long id){
		return noticiaRepository.findOne(id);
	}

	public List<Noticia> listarNoticiaUsuario(Usuario usuarioLogado) {
		return noticiaRepository.findByUsuarioLike(usuarioLogado);
	}
	
	
	
}
