package br.ufc.quixada.jornal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Comentario;

@Controller
@RequestMapping("{id}/comentar")
public class ComentarioController {

	@Autowired
	//private ComentarioService comentarioService;
	
	private static String FAZER_COMENTARIOS = "noticias/ListarNoticias"; 
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model, Comentario comentario){
		model.addAttribute(new Comentario());
		return FAZER_COMENTARIOS;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(){
		return "";
	}
	
}
