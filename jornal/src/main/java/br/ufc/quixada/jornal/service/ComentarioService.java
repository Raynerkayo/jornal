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
		List<Comentario> comentarioNoticiaUsuarios = comentarioRepository.findByUsuarioAndNoticiaLike(usuario.getId(), noticia.getId());
		return comentarioNoticiaUsuarios;
	}
	
}
