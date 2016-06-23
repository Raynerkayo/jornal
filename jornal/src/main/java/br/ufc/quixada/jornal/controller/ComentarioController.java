package br.ufc.quixada.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Comentario;
import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.ComentarioService;
import br.ufc.quixada.jornal.service.NoticiaService;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private NoticiaService noticiaService;

	private static String FAZER_COMENTARIOS = "comentarios/CadastroComentario";
	private static String LISTAR_COMENTARIOS = "comentarios/ListarComentarios";

	@RequestMapping(value = "/noticia/{id}", method = RequestMethod.GET)
	public String novo(@PathVariable("id") Long id, Model model, Comentario comentario) {
		model.addAttribute("noticia", noticiaService.buscarNoticiaPorId(id));
		model.addAttribute(new Comentario());
		return FAZER_COMENTARIOS;
	}

	@RequestMapping(value = "/noticia/{id}", method = RequestMethod.POST)
	public String salvar(@PathVariable("id") Long id, Comentario comentario, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Noticia noticia = noticiaService.buscarNoticiaPorId(id);
		if (usuario != null) {
			comentario.setNoticia(noticia);
			comentario.setUsuario(usuario);
			comentarioService.salvar(comentario);
			return "redirect:/noticias/listar";
		}
		return "redirect:/login/efetuarLogin";
	}

	@RequestMapping(value = "listar/comentarios/noticia/{id}", method = RequestMethod.GET)
	public String listarComentarioNoticia(@PathVariable("id") Noticia id, Comentario comentario, Model model){
		List<Comentario> comentariosNoticia = comentarioService.comentariosNoticiaId(id);
		model.addAttribute("comentarios", comentariosNoticia);
		return LISTAR_COMENTARIOS;
	}
	
}
