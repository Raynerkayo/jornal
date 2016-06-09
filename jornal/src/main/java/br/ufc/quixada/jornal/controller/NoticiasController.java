package br.ufc.quixada.jornal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.service.NoticiaService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	private static final String CADASTRAR_NOTICIAS = "noticias/CadastrarNoticias";
	private static final String LISTAR_NOTICIAS = "noticias/ListarNoticias";

	@Autowired
	private NoticiaService noticiaService;

	@RequestMapping("/nova")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(CADASTRAR_NOTICIAS);
		modelAndView.addObject(new Noticia());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Noticia noticia, Model model) {
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
	public String editar(@PathVariable("id") Noticia noticia, Model model){
		model.addAttribute("noticia", noticia);
		return CADASTRAR_NOTICIAS;
	}
	
	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id){
		noticiaService.excluir(id);
		return "redirect:/noticias/listar";
	}

}
