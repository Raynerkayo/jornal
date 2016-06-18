package br.ufc.quixada.jornal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.service.NoticiaService;

@Controller
@RequestMapping("/jornalista")
public class JornalistaController {
	
	@Autowired
	private NoticiaService noticiaService;
	
	private static String CADASTRO_NOTICIA = "noticias/CadastrarNoticias";
	private static final String LISTAR_NOTICIAS = "noticias/ListarNoticias";
	
	@RequestMapping(value = "/cadastrar/noticia", method = RequestMethod.GET)
	public String novaNoticia(@Validated Noticia noticia, Model model){
		model.addAttribute(new Noticia());
		return CADASTRO_NOTICIA;
	}
	
	//lembrar de perguntar como fazer para chamar o controlNoticia para cá. para evitar escrever o código outra vez
	
	@RequestMapping("listar/noticias/{id}")
	public String listarNoticiasJornalistas(@PathVariable("id") Long id, Model model){
		List<Noticia> noticias = noticiaService.listarNoticiasJornalista(id);
		model.addAttribute("noticias", noticias);
		return LISTAR_NOTICIAS;
	}
	
	@RequestMapping(value = "remover/noticias/{idNoticia}", method = RequestMethod.DELETE)
	public String removerNoticias(@PathVariable("id") Long id, Model model){
		noticiaService.excluir(id);
		return "redirect:/listar/noticias";
	}
	
	
	
	
	
	
	
}
