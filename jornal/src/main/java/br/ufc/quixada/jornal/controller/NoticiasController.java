package br.ufc.quixada.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.model.Secao;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.NoticiaService;
import br.ufc.quixada.jornal.service.SecaoService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	private static final String CADASTRAR_NOTICIAS = "noticias/CadastrarNoticias";
	private static final String LISTAR_NOTICIAS = "noticias/ListarNoticias";

	@Autowired
	private NoticiaService noticiaService;

	@Autowired
	private SecaoService secaoService;
	
	@RequestMapping("/nova")
	public String novo(Model model, Noticia noticia) {
		List<Secao> secoes = secaoService.listar();
		model.addAttribute("secoes", secoes);
		model.addAttribute(new Noticia());
		return CADASTRAR_NOTICIAS;
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String salvar(Noticia noticia, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		noticia.setUsuario(usuario);
		
			//recuperar seção
			Secao secao = new Secao();	
			secao = secaoService.buscarSecaoId(noticia.getSecao().getId());
			noticia.setSecao(secao);
			
			noticiaService.salvar(noticia);
			return "redirect:/noticias/listar";
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Noticia> noticias = noticiaService.listar();
		model.addAttribute("noticias", noticias);
		return LISTAR_NOTICIAS;
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable("id") Noticia noticia, Model model) {
		List<Secao> secoes = secaoService.listar();
		model.addAttribute("secoes", secoes);
		model.addAttribute("noticia", noticia);
		return CADASTRAR_NOTICIAS;
	}

	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		noticiaService.excluir(id);
		return "redirect:/noticias/listar";
	}

	@RequestMapping(value = "/jornalista/{id}", method = RequestMethod.GET)
	public String listarNoticiasJornalistas(@PathVariable("id") Long id, Model model) {
		List<Noticia> noticias = noticiaService.listarNoticiasJornalista(id);
		model.addAttribute("noticias", noticias);
		return LISTAR_NOTICIAS;
	}

	@RequestMapping(value = "/secao/{id}", method = RequestMethod.GET)
	public String listarNoticiasSecoes(@PathVariable("id") Long id, Model model) {
		List<Noticia> noticiasPorSecao = noticiaService.listarNoticiaSecao(id);
		model.addAttribute("noticias", noticiasPorSecao);
		return LISTAR_NOTICIAS;
	}

}