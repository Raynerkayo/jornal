package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Comentario;
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

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model) {
		model.addAttribute(new Comentario());
		return FAZER_COMENTARIOS;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(Comentario comentario, Long idNoticia, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario != null) {
			comentario.setNoticia(noticiaService.buscarNoticia(idNoticia));
			comentario.setUsuario(usuario);
			comentarioService.salvar(comentario);
			return "redirect:/noticias/listar/" + idNoticia;
		}
		return "redirect:/login/efetuarLogin";
	}

}
