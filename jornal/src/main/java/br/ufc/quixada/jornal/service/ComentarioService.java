package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Comentario;
import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.repository.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public void salvar(Comentario comentario){
		comentarioRepository.save(comentario);
	}
	
	public List<Comentario> findByComentariosIdUsuario(Long idUsuario){
		List<Comentario> comentarios = comentarioRepository.findByComentarioIdUsuario(idUsuario);
		return comentarios;
	}
	
	public List<Comentario> findByComentarioNoticia(Long idNoticia){
		List<Comentario> comentarios = comentarioRepository.findByComentarioIdNoticia(idNoticia);
		return comentarios;
	}
	
	public List<Comentario> comentarioNoticiaUsuario(Usuario usuario, Noticia noticia){
		return comentarioRepository.findByUsuarioAndNoticiaContains(usuario.getId(), noticia.getId());
	}
	
	public List<Comentario> comentariosNoticiaId(Long idNoticia){
		return comentarioRepository.findByNoticiaLike(idNoticia);
	}
	
}
